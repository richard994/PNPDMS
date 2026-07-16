##版本
  后端：java 14，Maven 3.8+
  数据库：MySql (utf8mb4)
  前端：JSP + jQuery，无构建流程 (无 npm/webpack)
  服务器：tomcat 9

##结构
```
Miju_Price_Calc/
│
├── src/
│   ├── main/
│       ├── java/Util        # Java文件，包含servlet、数据库存取
│       └── webapp/          # Web 文件 (JSP, CSS, JS, WEB-INF)
│   └── test/
│       └── java/Util        # JUnit 5 单元测试 (仅覆盖不依赖数据库的逻辑，见下方"测试"部分)
├── docs/                     # 部署、数据库复制、测试说明文档 (见下方"文档"部分)
├── pom.xml                  # 项目设置 (导入和构筑规则)
├── CLAUDE.md                # 面向 Claude Code 的项目说明 (英文)
└── README.md
```

#######前端
```
  WEB-INF/jspf/navbar.jsp    # 所有页面共用的顶部导航栏，通过 <jsp:include> 引入
    │                        # 引入前需设置 activePage (Home/Pricing/Material/Tracker/Account) 来高亮当前页面
    │                        # 以及可选的 accountVariant ("plain"=未登录的auth.jsp专用；"toggle"=index.jsp专用，登录前后都可访问，用JS切换显示)
    │                        # 注意：文件必须保持 .jsp 后缀，Tomcat 默认只把 *.jsp 交给JSP引擎处理，若命名为 .jspf 会被当作纯文本原样输出
  css/shared.css              # 所有页面共用的 CSS (#headbar、面包屑图标、.nav-link、.dropdown-menu)，取代了之前各页面重复内联的样式
    │                        # 引入顺序需在 Bootstrap CSS 之后，否则 Bootstrap 默认样式会覆盖这里的颜色设置

  index.jsp                  # 主页：顶部检索 + 两个按钮：一个前往价格计算页面createqp.jsp；一个前往新产品开发录入页面AddNewDev.jsp
    ├── js/index.js
    └── css/index.css

  auth.jsp                   # 登陆页面
    └── css/auth.css

  createqp.jsp success.jsp                 # 价格计算页面及其对应的保存成功页面。createqp.jsp中主表格 PDform submit后前往CalcService储存价格及信息至数据库，随后跳转到success.jsp。
    │                                      # CreateService会发送纱线价格json格式到jsp，储存在var mats，之后所有运算都在当前文件进行。
    │                                      # CreateService会发送var edit（检验当前是否为编辑状态）；var quote（编辑目标的基本信息）；var quoteMats（编辑目标的所有纱线信息）
    ├── js/createqp.js                     # 若当前操作为edit，则会调用populateAllInput()函数预填入所有信息
    └── css/createqp.css

  pricing.jsp                  # 已计算价格产品的看板，含搜索、筛选、排序功能
    │                          # 每个产品价格带有三个按钮：edit按钮会前往CreateService准备信息回到createqp.jsp进行编辑；copy会前往CalcService(action=copy)复制当前产品并留在当前页面；delete会前往CalcService(action=delete)删除
    ├── js/pricing.js
    └── css/pricing.css 

  material.jsp                 # 纱线看板，带有搜索功能。可以根据erp信息归纳统计纱线分类数量。
    ├── js/material.js

  AddNewDev.jsp DevSuccess.jsp                 # 开发录入页面及对应的录入成功页面。AddNewDev.jsp中主表格 NDform submit后前往SaveNewDevService储存信息至数据库，随后跳转到DevSuccess.jsp。
    │                                          # SaveNewDevService 通过 action 参数区分操作：create(新建，默认)/edit(编辑)/delete(删除)/duplicate(复制)/multedit(批量编辑的保存目标)
    │                                          # NewDevService会发送当前操作用户var user（用于记录操作人员用于log日志，经JsonUtil转义防止内容中的特殊字符破坏页面）
    │                                          # NewDevService会发送var create（检验当前是否为创建状态）
    │                                          # NewDevService会发送var edit（检验当前是否为编辑状态）
    │                                          # NewDevService会发送var dev（开发信息JSON），var comments（评论信息JSON），var logs（13条最新日志信息JSON），var fullLogs（所有日志信息JSON）
    ├── js/addnewdev.js                        # 若当前操作为edit，则会调用populateAllInput()函数预填入所有开发信息，评论，并显示日志。
    │                                          # 图片（面料图/PID/测试报告）上传至 AWS S3，经 SaveNewDevService 处理，单文件20MB/单次请求60MB上限
    └── css/addnewdev.css css/devsuccess.css

  tracker.jsp                  # 已录入开发的看板，含搜索、筛选、排序功能
    │                          # 每个产品价格带有两个按钮：edit按钮会前往NewDevService准备信息回到AddNewDev.jsp进行编辑；Duplicate会前往SaveNewDevService(action=duplicate)复制当前产品并留在当前页面
    │                          # AddNewDevBtn 按钮会前往AddNewDev.jsp创建新开发；MultEditBtn 按钮会前往MultEdit.jsp，允许编辑多个开发信息
    │                          # export 按钮会前往ExportService.java将当前筛选状态的所有开发导出至excel
    │                          # 筛选逻辑由 FilterService 处理：默认不勾选任何条件时只显示在产(active)的开发，同时勾选"Active"和"Inactive"才会显示全部
    │                          # 目前筛选查询有两条并行路径：旧的Java内存筛选（实际展示结果的路径）和新的DevData.getFilteredDevelopments SQL筛选（"影子模式"，仅记录比对日志，见docs/QA_CHECKLIST.md）
    ├── js/tracker.js          
    └── css/tracker.css

  MultEdit.jsp                  # 同时编辑多个开发产品的信息
    │                           # MultEdit会发送var dev（所有产品信息JSON）
    │                           # 用户输入的产品如果存在，就会跳出可以更改的数据栏供编辑，提交后会前往SaveNewDevService(action=multedit)
    ├── js/multedit.js          
    └── css/MultEdit.css
```

#######后端
  所有 servlet 通过 `@WebServlet` 注解自行注册，没有 web.xml (旧的 web.xml 曾放错路径导致 Tomcat 从未真正读取过它，已删除)。

  数据库连接统一经过 `Util/DbUtil.java`：优先查找 JNDI 数据源 `jdbc/MijuPrice`（生产环境的连接池配置，见 `docs/DEPLOYMENT.md`），找不到时回退到 `Util/Constant.java` 中硬编码的 `DriverManager` 连接（本地开发默认使用这条路径）。新增数据存取代码时应使用 `DbUtil.getConnection()`，不要再手写 `DriverManager.getConnection(...)`。

  `Util/JsonUtil.java` 负责把要嵌入 JSP `<script>` 标签的 JSON 安全转义（防止评论/备注等自由文本中含有 `</script>` 破坏页面或注入脚本）。

  数据库总共7个表：
    comment            # 对应开发的评论：
    
    development        # 开发：
    
    log                # 对应开发的日志：
    
    materials          # 对应计算过价格的纱线信息：
    
    mats               # 来自erp的纱线信息：mtrename 纱线英文名称, mtrtype 纱线类型, countgu 股数, countzhi 织数, 
                         prprice 喷染价格, colorprice 色纺价格, drprice 段染价格, whiteprice 白纱价格, dyeprice 染色价格, 
                         whiteloss 白纱损耗, warploss 经纱损耗, colorloss 色纺纱损耗, mtrid 中亚型号, material_id 主钥
                         
    quotes             # 计算过的价格：create_date 创建日期, model_name 产品型号, 
                         fabric_type 面料类型, finish_module 后处理方式, memo 批注, quote_id 主钥, 
                         ppcm 纬密, total_warp 总经, weight 克重, sale_price 售价
                         
    user               # 用户：user_id 主钥, name 用户名称, email 邮箱, password 密码

  每日后台同步：`Scheduler`（`@WebListener`）在应用启动时开启一个每日执行一次的任务，调用 `DataParser` 从外部 ERP 接口拉取纱线与开发数据，写入 `mats`/`development` 表。

#######测试
  `mvn test` 运行 `src/test/java/Util` 下的 JUnit 5 测试，只覆盖不依赖数据库的纯逻辑（`JsonUtil`、`Developments.compare`/`checkPhase`、`DataParser` 的解析函数、`FilterService.filter`）。其余需要数据库/页面渲染/文件上传的部分靠人工按 `docs/QA_CHECKLIST.md` 走查，详见 `docs/TESTING.md`。

#######文档 (docs/)
```
  DEPLOYMENT.md            # 部署说明：JNDI 数据库连接池配置步骤、字符编码问题、文件上传限制
  context.xml.template     # 配置 JNDI 数据源用的 Tomcat context.xml 模板（服务器端专用，不含真实密码）
  LOCAL_DB_SETUP.md        # 如何用 mysqldump 把生产数据库复制一份到本地测试
  TESTING.md               # 自动化测试范围说明
  QA_CHECKLIST.md          # 人工测试走查清单，含 SQL 筛选重构的影子验证/切换流程
```
