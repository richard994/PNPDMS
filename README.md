##版本
  后端：java 17+，Maven 3.8+
  数据库：MySql
  前端：JSP
  服务器：tomcat 9

##结构
```
Miju_Price_Calc/
│
├── src/
│   ├── main/
│       ├── java/Util        # Java文件，包含servlet、数据库录读
│       └── webapp/          # Web 文件 (JSP, CSS, JS, WEB-INF)
├── pom.xml                  # 项目设置 (导入和构筑规则)
└── README.md                
```

#######前端
```
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
    │                          # 每个产品价格带有三个按钮：edit按钮会前往CreateService准备信息回到createqp.jsp进行编辑；copy会复制当前产品并留在当前页面；delete为删除
    ├── js/pricing.js
    └── css/pricing.css 

  material.jsp                 # 纱线看板，带有搜索功能。可以根据erp信息归纳统计纱线分类数量。
    ├── js/material.js

  AddNewDev.jsp DevSuccess.jsp                 # 开发录入页面及对应的录入成功页面。AddNewDev.jsp中主表格 NDform submit后前往SaveNewDevService储存信息至数据库，随后跳转到DevSuccess.jsp。
    │                                          # NewDevService会发送当前操作用户var user（用于记录操作人员用于log日志）
    │                                          # NewDevService会发送var create（检验当前是否为创建状态）
    │                                          # NewDevService会发送var edit（检验当前是否为编辑状态）
    │                                          # NewDevService会发送var dev（开发信息JSON），var comments（评论信息JSON），var logs（13条最新日志信息JSON），var fullLogs（所有日志信息JSON）
    ├── js/addnewdev.js                        # 若当前操作为edit，则会调用populateAllInput()函数预填入所有开发信息，评论，并显示日志。
    │                                          # 图片录入使用的是 FileReader 对象
    └── css/addnewdev.css css/devsuccess.css

  tracker.jsp                  # 已录入开发的看板，含搜索、筛选、排序功能
    │                          # 每个产品价格带有两个按钮：edit按钮会前往NewDevService准备信息回到AddNewDev.jsp进行编辑；Duplicate会复制当前产品并留在当前页面
    │                          # AddNewDevBtn 按钮会前往AddNewDev.jsp创建新开发；MultEditBtn 按钮会前往MultEdit.jsp，允许编辑多个开发信息
    │                          # export 按钮会前往ExportService.java将当前筛选状态的所有开发导出至excel
    ├── js/tracker.js          
    └── css/tracer.css

  MultEdit.jsp                  # 同时编辑多个开发产品的信息
    │                           # MultEdit会发送var dev（所有产品信息JSON）
    │                           # 用户输入的产品如果存在，就会跳出可以更改的数据栏供编辑，提交后会前往SaveNewDevService
    ├── js/multedit.js          
    └── css/MultEdit.css
```

#######后端

