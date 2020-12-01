define({ "api": [
  {
    "type": "get",
    "url": "/aaa/findList?sdate",
    "title": "",
    "description": "<p>查询集合</p>",
    "name": "查询集合",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "sdate",
            "description": "<p>用户的姓名</p>"
          }
        ]
      }
    },
    "group": "index",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "userNotFound",
            "description": "<p>The <code>id</code></p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "/index"
      }
    ],
    "version": "0.0.0",
    "filename": "E:/springBoot-test/springboot-api/src/main/java/com/example/demo/controller/ApiDocController.java",
    "groupTitle": "index"
  }
] });
