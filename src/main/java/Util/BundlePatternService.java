package Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/BundlePatternService")
public class BundlePatternService extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public BundlePatternService() {}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ---【统一修复 IE9 跨域不发 Origin 头的问题】---
		String origin = request.getHeader("Origin");
		// 获取真实的请求端 IP (兼容 IE9)
		String remoteIp = request.getRemoteAddr();

		boolean isAllowed = false;
		String allowedOrigin = "http://10.0.0.15:8080"; // 兜底默认值

		// 1. 判断 IP 是否在白名单内
		if (remoteIp != null && ("10.0.0.15".equals(remoteIp) || "10.0.0.100".equals(remoteIp))) {
			isAllowed = true;
		}
		// 2. 判断 Origin 请求头是否在白名单内
		if (origin != null && (origin.contains("10.0.0.15") || origin.contains("10.0.0.100"))) {
			isAllowed = true;
			allowedOrigin = origin; // 优先使用浏览器发来的确切 Origin
		}

		// 3. 只要通过白名单，强制写入跨域响应头
		if (isAllowed) {
			response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
		}

		// ---【处理 IE9/IE11 都会发的 OPTIONS 预检请求】---
		String method = request.getMethod();
		if ("OPTIONS".equalsIgnoreCase(method)) {
			response.setStatus(HttpServletResponse.SC_OK);
			return; // 预检请求直接结束
		}

		// ---【修改3】获取客户端 IP，判断白名单，跳过登录检查 ---
		String remoteAddr = request.getRemoteAddr();
		// 如果是经过代理，可以通过 request.getHeader("X-Forwarded-For") 获取真实 IP，这里按你要求的 10.0.0.15/100 直接对比
		boolean isWhitelistedIp = false;
		if (remoteAddr != null) {
			if ("10.0.0.15".equals(remoteAddr) || "10.0.0.100".equals(remoteAddr)) {
				isWhitelistedIp = true;
			}
		}

		HttpSession session = request.getSession();
		// 如果是白名单 IP，或者已经登录，或者带有 remote 参数（ERP调用），则不跳转登录页
		if (!isWhitelistedIp && session.getAttribute("loggedin") == null && request.getParameter("remote") == null) {
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
			return;
		}

		// ---【修改4】防止跳过登录后 userName 为 null 导致后续空指针报错 ---
		String username = (String) session.getAttribute("userName");
		if (username == null) {
			if (isWhitelistedIp) {
				username = "Remote_User"; // 给白名单 IP 一个默认用户名
				session.setAttribute("userName", username);
				session.setAttribute("loggedin", true); // 可选：模拟已登录
			} else {
				username = "";
			}
		}
		request.setAttribute("user", username);

		// ---【以下是你原本的业务逻辑，保持不变】---
		String action = request.getParameter("action");
		int devId = 0;
		if ("view".equals(action) || "edit".equals(action) || "create".equals(action) || action == null || action.isEmpty()) {
			String devIdStr = request.getParameter("devId");
			if (devIdStr != null && !devIdStr.isEmpty()) {
				devId = Integer.parseInt(devIdStr);
				request.setAttribute("devid", devId);
			}
		}

		DevData devdata = new DevData();
		PriceData pd = new PriceData();
		ArrayList<Mats> pds = pd.getMatList();

		String remoteuser = request.getParameter("remote");
		if("remote".equals(remoteuser)){//ERP调用时使用
			if("query".equals(action))
				doQuery(response,request);
			if("queryitem".equals(action)) {
				doQueryItem(response,request);
			}
			if("finish".equals(action)){
				doUpdateFlag(response,request);
			}
		} else if (action == null || "view".equals(action) || "".equals(action) ) {
			request.getRequestDispatcher("/NewBundle.jsp").forward(request, response);
		} else if ("edit".equals(action) ) {
			request.setAttribute("edit", true);
			ObjectMapper objectMapper = new ObjectMapper();
			String pdss = objectMapper.writeValueAsString(pds);
			ArrayList<MtrColors> aaa = devdata.getMtrColors();
			String mtrcolor = objectMapper.writeValueAsString(aaa);
			request.setAttribute("mtrcolors",mtrcolor);
			request.setAttribute("mtrspec",pdss);

			String packageid = request.getParameter("packageid");
			JSONObject packageds = new JSONObject();
			packageds = getPackageData(packageid);
			String pkg = packageds.toString();
			request.setAttribute("data",pkg);
			request.getRequestDispatcher("/EditNewBundle.jsp").forward(request, response);
		} else if("delete".equals(action)){
			String packageid = request.getParameter("packageid");
			boolean state = deletePackage(packageid);
			String msg = state ? "Deleted successfully！" : "Deletion failed！";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println(msg);
		} else if("queryitem".equals(action)){
			doQueryItem(response,request);
		} else if("query".equals(action)){
			doQuery(response,request);
		} else if("savenew".equals(action)){
			doSaveNew(response,request);
		} else if("saveedit".equals(action)){
			doSaveEdit(response,request);
		} else if("finish".equals(action)){
			doUpdateFlag(response,request);
		} else if("create".equals(action)) {
			Developments dev = devdata.getDevelopmentById(devId);
			ArrayList<Comment> comments = devdata.getCommentsById(devId);
			ArrayList<Log> logs = devdata.getLogsById(devId);
			List<Log> showLogs = logs.subList(0, Math.min(logs.size(), 13));
			ArrayList<MtrColors> aaa = devdata.getMtrColors();

			ObjectMapper objectMapper = new ObjectMapper();
			String devJson = objectMapper.writeValueAsString(dev);
			String commentJson = objectMapper.writeValueAsString(comments);
			String logJson = objectMapper.writeValueAsString(showLogs);
			String fullLogJson = objectMapper.writeValueAsString(logs);
			String mtrcolor = objectMapper.writeValueAsString(aaa);
			String pdss = objectMapper.writeValueAsString(pds);

			request.setAttribute("dev", devJson);
			request.setAttribute("comments", commentJson);
			request.setAttribute("logs", logJson);
			request.setAttribute("fulllogs", fullLogJson);
			request.setAttribute("mtrcolors",mtrcolor);
			request.setAttribute("mtrspec",pdss);
			request.getRequestDispatcher("/AddNewBundle.jsp").forward(request, response);
		}
	}

	/**
	 * 通用更改标志 (确认/反确认接口)
	 */
	private void doUpdateFlag(HttpServletResponse response, HttpServletRequest request) {
		String packageid = request.getParameter("packageid");
		String field = request.getParameter("field");
		String value = request.getParameter("upvalue");
		Connection conn = null;
		PrintWriter out = null;
		Statement st = null;
		JSONObject back = new JSONObject();
		back.put("success", true);

		try {
			out = response.getWriter();
			response.setContentType("application/json;charset=UTF-8"); // 防止乱码

			conn = DbUtil.getConnection();
			// 【核心修复】此处必须初始化 Statement，否则 st 为空会报错
			st = conn.createStatement();

			String sql = "update mijuprice.dsgpackageh set " + field + "='" + value + "' where id = '" + packageid + "'";
			st.execute(sql);

		} catch (Exception e) {
			back.put("success", false);
			back.put("msg", e.getMessage());
		} finally {
			try {
				if (st != null) st.close();
				if (conn != null && !conn.isClosed()) conn.close();
			} catch (Exception e) {
			}
		}
		out.println(back);
	}

	/**
	 * 查询单个包袱样的所有信息
	 * @param response
	 * @param request
	 */
	private void doQueryItem(HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		Connection conn = null;
		String packageid = request.getParameter("packageid");
		JSONObject packageds = new JSONObject();
		try{
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			conn = DbUtil.getConnection();
			packageds = getPackageData(packageid);

			// 【核心新增】判断是否为 JSONP 请求
			String callback = request.getParameter("callback");
			String jsonStr = packageds.toString();
			if (callback != null && !callback.trim().isEmpty()) {
				// 如果是 JSONP，返回 JavaScript 代码
				response.setContentType("application/javascript;charset=UTF-8");
				out.println(callback + "(" + jsonStr + ");");
			} else {
				// 正常 JSON
				out.println(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(conn != null && !conn.isClosed()) conn.close();
			} catch (Exception ee){}
		}
	}

	/**
	 * 保存编辑数据
	 * @param response
	 * @param request
	 */
	private void doSaveEdit(HttpServletResponse response, HttpServletRequest request) {
		// 【核心修复1】必须要在方法最开头获取 out 并设置编码
		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		PriceData pd = new PriceData();
		Connection conn = null;
		Map<String,String> back = new HashMap<String,String>();
		back.put("success","false"); // 默认为 false，成功时再改为 true

		try {
			conn = DbUtil.getConnection();
			//格式化参数
			String head = request.getParameter("head");
			String detail = request.getParameter("detail");
			JSONObject jhead = new JSONObject(head);
			JSONArray jdetail = new JSONArray(detail);
			//具体保存过程
			back = doExecuteDataBase(conn,jhead,jdetail,"update");
		} catch (Exception e) {
			back.put("success","false");
			back.put("msg", e.getMessage());
			e.printStackTrace(); // 控制台会打印完整错误堆栈
		} finally {
			try{
				// 【核心修复2】必须加上 conn != null 的判断，否则连接失败时这里也会空指针
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception es) {
				es.printStackTrace();
			}
		}
		// 输出标准的 JSON 字符串
		out.println(new JSONObject(back).toString());
	}

	/**
	 * 保存过程，用于保存新增加的包袱样
	 * @param response
	 * @param request
	 */
	/**
	 * 保存过程，用于保存新增加的包袱样
	 */
	private void doSaveNew(HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		PriceData pd = new PriceData();
		Connection conn = null;
		Map<String,String> back = new HashMap<String,String>();
		back.put("success","false");

		try {
			conn = DbUtil.getConnection();
			String head = request.getParameter("head");
			String detail = request.getParameter("detail");
			JSONObject jhead = new JSONObject(head);
			JSONArray jdetail = new JSONArray(detail);

			// 执行保存
			back = doExecuteDataBase(conn, jhead, jdetail, "insert");

		} catch (Exception e) {
			// 【核心修改】这里不再只返回模糊的 msg，而是返回堆栈中最核心的错误信息
			String detailedError = "数据库异常: " + e.getMessage();
			// 如果是 NullPointerException，它会明确告诉你是哪一行
			// 如果是 SQLException，它会告诉你是"Duplicate entry"还是"Data too long"

			// 【极度推荐】在后台控制台打印出完整的错误堆栈，您去 Tomcat 控制台一看便知
			e.printStackTrace();

			back.put("msg", detailedError);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) conn.close();
			} catch (Exception es) {}
		}

		out.println(new JSONObject(back).toString());
	}


	/**
	 * 具体执行过程，组织sql语句，保存数据到数据库中
	 * @param conn
	 * @param jhead
	 * @param jdetail
	 * @param optflag
	 * @return
	 */
	/**
	 * 具体执行过程，组织sql语句，保存数据到数据库中
	 */
	private Map<String, String> doExecuteDataBase(Connection conn, JSONObject jhead, JSONArray jdetail, String optflag) throws  Exception {
		Map<String,String> msg = new HashMap<String,String>();
		Statement st = null;
		ResultSet rs = null;
		String sql = "";

		// 【核心修复】将原来的单行短路逻辑改为显式独立判断，防止任何 null 绕过
		java.util.function.Function<String, String> sqlVal = (val) -> {
			// 1. 如果为 null，直接返回 "NULL"（不加引号，数据库会自动识别为 NULL）
			if (val == null) {
				return "NULL";
			}
			// 2. 如果为空字符串或只有空格，也直接返回 "NULL"
			if (val.trim().isEmpty()) {
				return "NULL";
			}
			// 3. 正常字符串，转义单引号后加上 SQL 单引号返回
			return "'" + val.replace("'", "''") + "'";
		};

		try{
			conn.setAutoCommit(false);
			st = conn.createStatement();

			if("update".equals(optflag)){
				// 1. 更新主表
				sql = "update mijuprice.dsgpackageh set " +
						"patternid=" + sqlVal.apply(jhead.getString("patternid")) + "," +
						"flower=" + sqlVal.apply(jhead.getString("flower")) + "," +
						"DESIGNER=" + sqlVal.apply(jhead.getString("DESIGNER")) + "," +
						"DSGDATE=" + sqlVal.apply(jhead.getString("DSGDATE")) + "," +
						"DISC=" + sqlVal.apply(jhead.getString("DISC")) + "," +
						"CONST=" + sqlVal.apply(jhead.getString("CONST")) + "," +
						"picks_inch=" + sqlVal.apply(jhead.getString("picks_inch")) + "," +
						"picks_cm=" + sqlVal.apply(jhead.getString("picks_cm")) + "," +
						"warp=" + sqlVal.apply(jhead.getString("warp")) + "," +
						"breadth=" + sqlVal.apply(jhead.getString("breadth")) + "," +
						"yarn=" + sqlVal.apply(jhead.getString("yarn")) + "," +
						"totalends=" + sqlVal.apply(jhead.getString("totalends")) + "," +
						"target_price=" + sqlVal.apply(jhead.getString("target_price")) + "," +
						"shipto=" + sqlVal.apply(jhead.getString("shipto")) +
						" where id='" + jhead.getString("id") + "'";
				st.execute(sql);

				// 2. 先删除该主表下的所有子表记录（保证子表与前端提交完全同步）
				sql = "delete from mijuprice.dsgpackageb where id='" + jhead.getString("id") + "'";
				st.execute(sql);

				// 3. 重新插入当前所有子表记录（无论是否已有ID，均重新插入）
				for (int i = 0; i < jdetail.length(); i++) {
					JSONObject item = jdetail.getJSONObject(i);
					sql = "insert into mijuprice.dsgpackageb(id,selectors,yarntype,shorts,idx,colorcode,colorname,color) " +
							"values('" + jhead.getString("id") + "', " +
							sqlVal.apply(item.getString("selectors")) + ", " +
							sqlVal.apply(item.getString("yarntype")) + ", " +
							sqlVal.apply(item.getString("shorts")) + ", " +
							sqlVal.apply(item.getString("idx")) + ", " +
							sqlVal.apply(item.getString("colorcode")) + ", " +
							sqlVal.apply(item.getString("colorname")) + ", " +
							sqlVal.apply(item.getString("color")) + ")";
					st.execute(sql);
				}
			}

			if("insert".equals(optflag)){
				String id = "1";
				sql = "select max(id) id from mijuprice.dsgpackageh";
				rs = st.executeQuery(sql);
				if(rs.next()){
					id = rs.getString("id");
					// 防止表为空导致 Integer.valueOf(null) 报错
					if(id == null || id.equals("")) {
						id = "1";
					} else {
						id = String.valueOf(Integer.valueOf(id) + 1);
					}
				}

				// 【插入语句：使用 sqlVal 自动处理所有数值/日期字段的空字符串问题】
				sql = "insert into mijuprice.dsgpackageh(id,patternid,flower,DESIGNER,DSGDATE,DISC,CONST,picks_inch,picks_cm,warp,breadth,yarn,totalends,target_price,shipto) " +
						"values('" + id + "', " +
						sqlVal.apply(jhead.getString("patternid")) + ", " +
						sqlVal.apply(jhead.getString("flower")) + ", " +
						sqlVal.apply(jhead.getString("designer")) + ", " +
						sqlVal.apply(jhead.getString("dsgdate")) + ", " +
						sqlVal.apply(jhead.getString("disc")) + ", " +
						sqlVal.apply(jhead.getString("const")) + ", " +
						sqlVal.apply(jhead.getString("picks_inch")) + ", " +
						sqlVal.apply(jhead.getString("picks_cm")) + ", " +
						sqlVal.apply(jhead.getString("warp")) + ", " +
						sqlVal.apply(jhead.getString("breadth")) + ", " +
						sqlVal.apply(jhead.getString("yarn")) + ", " +
						sqlVal.apply(jhead.getString("totalends")) + ", " +
						sqlVal.apply(jhead.getString("target_price")) + ", " +
						sqlVal.apply(jhead.getString("shipto")) + ")";
				st.execute(sql);
				for(int i=0;i<jdetail.length();i++){
					JSONObject item = jdetail.getJSONObject(i);
					sql = "insert into mijuprice.dsgpackageb(id,selectors,yarntype,shorts,idx,colorcode,colorname,color) " +
							"values('" + id + "', " +
							sqlVal.apply(item.getString("selectors")) + ", " +
							sqlVal.apply(item.getString("yarntype")) + ", " +
							sqlVal.apply(item.getString("shorts")) + ", " +
							sqlVal.apply(item.getString("idx")) + ", " +
							sqlVal.apply(item.getString("colorcode")) + ", " +
							sqlVal.apply(item.getString("colorname")) + ", " +
							sqlVal.apply(item.getString("color")) + ")";
					st.execute(sql);
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
			// 【核心修复】成功执行完毕后，强制写入 success 字段
			msg.put("success", "true");
		} catch (Exception e) {
			throw e;
		} finally {
			try{
				if(rs!=null) rs.close();
				if(st!=null) st.close();
			} catch (Exception e) {}
		}
		return msg;
	}

	/**
	 * 查询界面 查询记录
	 * @param response
	 * @param request
	 */
	private void doQuery(HttpServletResponse response, HttpServletRequest request) {
		PriceData pd = new PriceData();
		Statement st = null;
		ResultSet rs = null;
		//根据参数 配置sql语句
		String sql = makeSql(request);
		//List<Map<String,String>> items = new ArrayList<Map<String,String>>();
		JSONArray items = new JSONArray();
		PrintWriter out = null;
		Connection conn = null;
		try{
			// 【必加】修复前端收到的中文变成 ???? 的问题
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			conn = DbUtil.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while(rs.next()){
				//Map<String,String> item = new HashMap<String,String>();
				JSONObject item = new JSONObject();
				item = parseHeadData(rs);
				items.put(item);
			}
			// 【核心新增】判断是否为 JSONP 请求
			String callback = request.getParameter("callback");
			String jsonStr = items.toString();
			if (callback != null && !callback.trim().isEmpty()) {
				response.setContentType("application/javascript;charset=UTF-8");
				out.println(callback + "(" + jsonStr + ");");
			} else {
				out.println(jsonStr);
			}
		}catch (Exception e){
			e.printStackTrace(); // 加上这一行，控制台会输出报错信息
		}finally {
			try{
				if(rs!=null)
					rs.close();
				if(st!=null)
					st.close();
				if(conn!=null && !conn.isClosed())
					conn.close();
			} catch (Exception es) {
			}
		}
		//out.println(items);
	}

	/**
	 * 查询记录时，将参数配置到查询语句中
	 */
	private String makeSql(HttpServletRequest request) {
		String sql = "";
		String designer = request.getParameter("designer");
		String patternid = request.getParameter("patternid");
		String flowers = request.getParameter("flowers");
		String bdate = request.getParameter("bdate");
		String edate = request.getParameter("edate");
		String breadth = request.getParameter("breadth");
		String warp = request.getParameter("warp");
		String yeartype = request.getParameter("yeartype");
		String totalends = request.getParameter("totalends");
		String price = request.getParameter("price");
		String confirmflag = request.getParameter("confirmflag");
		String quoteflag = request.getParameter("quoteflag");

		// 修复1：强制加上 schema 前缀，确保能查到表
		sql = "select * from mijuprice.dsgpackageh where 1=1 ";

		// 修复2：将参数逻辑改为正确的“不为空才拼接”
		if (designer != null && !designer.trim().isEmpty()) {
			sql += " and DESIGNER like '%" + designer + "%' ";
		}
		if (patternid != null && !patternid.trim().isEmpty()) {
			sql += " and patternid like '%" + patternid + "%' ";
		}
		if (flowers != null && !flowers.trim().isEmpty()) {
			sql += " and flower like '%" + flowers + "%' ";
		}

		// 修复3：日期比较的字段名必须改成 DSGDATE，不能用 designer
		if (bdate != null && !bdate.trim().isEmpty()) {
			sql += " and DSGDATE >= '" + bdate + "' ";
		}
		if (edate != null && !edate.trim().isEmpty()) {
			sql += " and DSGDATE <= '" + edate + "' ";
		}

		if (breadth != null && !breadth.trim().isEmpty()) {
			sql += " and breadth = '" + breadth + "' ";
		}
		if (warp != null && !warp.trim().isEmpty()) {
			sql += " and warp like '%" + warp + "%' ";
		}
		if (yeartype != null && !yeartype.trim().isEmpty()) {
			sql += " and yeartype like '%" + yeartype + "%' ";
		}
		if (totalends != null && !totalends.trim().isEmpty()) {
			sql += " and totalends = '" + totalends + "' ";
		}
		if (price != null && !price.trim().isEmpty()) {
			sql += " and target_price = '" + price + "' ";
		}
		if (confirmflag != null && !confirmflag.trim().isEmpty()) {
			sql += " and confirmflag = '" + confirmflag + "' ";
		}
		if (quoteflag != null && !quoteflag.trim().isEmpty()) {
			sql += " and quoteflag = '" + quoteflag + "' ";
		}
		// 【新增】按照自增主键 id 倒序，确保最新数据在表格第一行
		sql += " order by id desc";
		return sql;
	}


	/**
	 * 删除包袱样记录
	 * @param packageid
	 * @return
	 */
	private boolean deletePackage(String packageid) {
		boolean bl = true;
		Statement st = null;
		Connection conn = null;
		try{
			conn = DbUtil.getConnection();
			st = conn.createStatement();
			String sql = "";
			sql = "delete from dsgpackageb where id = '"+packageid+"' ";
			st.execute(sql);
			sql = "delete from dsgpackageh where id = '"+packageid+"'";
			st.execute(sql);
		}catch (Exception e){
			bl = false;
		}finally {
			try{
				if(st!=null)
					st.close();
				if(conn!=null && !conn.isClosed())
					conn.close();
			}catch (Exception es){

			}
		}
		return bl;
	}

	/**
	 * 格式化包袱样表头数据，将查询到的一行表头数据转换成Map数据，项目没有导入json，就没有使用json格式
	 * @param rs
	 * @return
	 */
	public JSONObject parseHeadData(ResultSet rs){
		//Map<String,String> item = new HashMap<String,String>();
		JSONObject item = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			item.put("id",rs.getString("id"));
			item.put("patternid",rs.getString("patternid")==null?"":rs.getString("patternid"));
			item.put("flower",rs.getString("flower")==null?"":rs.getString("flower"));
			item.put("DESIGNER",rs.getString("DESIGNER")==null?"":rs.getString("DESIGNER"));
			java.sql.Date sd = rs.getDate("dsgdate");
			if(sd == null)
				item.put("DSGDATE","");
			else{
				Date dt = new Date(rs.getDate("dsgdate").getTime());
				item.put("DSGDATE",sf.format(new Date(rs.getDate("DSGDATE").getTime())));
			}
			item.put("DISC",rs.getString("DISC")==null?"":rs.getString("DISC"));
			item.put("CONST",rs.getString("CONST")==null?"":rs.getString("CONST"));
			item.put("picks_inch",rs.getString("picks_inch")==null?"":rs.getString("picks_inch"));
			item.put("picks_cm",rs.getString("picks_cm")==null?"":rs.getString("picks_cm"));
			item.put("warp",rs.getString("warp")==null?"":rs.getString("warp"));
			item.put("breadth",rs.getString("breadth")==null?"":rs.getString("breadth"));
			item.put("yarn",rs.getString("yarn")==null?"":rs.getString("yarn"));
			item.put("totalends",rs.getString("totalends")==null?"":rs.getString("totalends"));
			item.put("target_price",rs.getString("target_price")==null?"":rs.getString("target_price"));
			item.put("shipto",rs.getString("shipto")==null?"":rs.getString("shipto"));
			// 【核心新增】务必补全这两个字段，否则前台无法显示
			item.put("confirmflag", rs.getString("confirmflag") == null ? "" : rs.getString("confirmflag"));
			item.put("quoteflag", rs.getString("quoteflag") == null ? "" : rs.getString("quoteflag"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	/**
	 * 格式化包袱样没明细数据，将查询到的明细表一行数据格式化成Map数据
	 * @param rs
	 * @return
	 */
	public JSONObject parseDetailData(ResultSet rs){
		//Map<String,String> item = new HashMap<String,String>();
		JSONObject item = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			item.put("id",rs.getString("id"));
			item.put("yarntype",rs.getString("yarntype")==null?"":rs.getString("yarntype"));
			item.put("shorts",rs.getString("shorts")==null?"":rs.getString("shorts"));
			item.put("idx",rs.getString("idx")==null?"":rs.getString("idx"));
			item.put("colorcode",rs.getString("colorcode")==null?"":rs.getString("colorcode"));
			item.put("colorname",rs.getString("colorname")==null?"":rs.getString("colorname"));
			item.put("color",rs.getString("color")==null?"":rs.getString("color"));
			item.put("selectors",rs.getString("selectors")==null?"":rs.getString("selectors"));
		} catch (Exception e) {

		}
		return item;
	}

	/**
	 * 查询指定的一条包袱样数据，包含表头数据和明细数据
	 * @param packageid
	 * @return
	 */
	private JSONObject getPackageData(String packageid) {
		//Map<String,Object> data = new HashMap<String, Object>() ;
		JSONObject data = new JSONObject();
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "SELECT * FROM mijuprice.dsgpackageh where id = '"+packageid+"'";
			conn = DbUtil.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			//Map<String,String> head = new HashMap<String,String>();
			JSONObject head = new JSONObject();
			if(rs.next()){
				head = parseHeadData(rs);
			}
			data.put("head",head);

			sql = "SELECT * FROM mijuprice.dsgpackageb where id = '"+packageid+"'";
			rs = st.executeQuery(sql);
			//List<Map<String,String>> items = new ArrayList<Map<String,String>>();
			JSONArray items = new JSONArray();
			while(rs.next()){
				//Map<String,String> item = new HashMap<String,String>();
				JSONObject item = new JSONObject();
				item = parseDetailData(rs);
				items.put(item);
			}
			data.put("items",items);
		}catch (Exception e){

		}finally {
			try{
				if(rs!=null)
					rs.close();
				if(st!=null)
					st.close();
				if(conn!=null && !conn.isClosed())
					conn.close();
			}catch (Exception es){

			}
		}
		return data;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}