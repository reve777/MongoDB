{
	"info": {
		"_postman_id": "59dcb9a6-eff4-4b8c-bcae-9beda3075d2e",
		"name": "mongoDB",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "24782644"
	},
	"item": [
		{
			"name": "forexAPI從 DB取出日期區間內美元 /台幣的歷史資料",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"startDate\":\"20240807\",\r\n    \"endDate\":\"20240809\",\r\n    \"currency\":\"usd\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/portfolio/api/forex/historical",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"portfolio",
						"api",
						"forex",
						"historical"
					]
				}
			},
			"response": []
		},
		{
			"name": "呼叫 API取得外匯成交資料",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8080/portfolio/api/forex/create",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"portfolio",
						"api",
						"forex",
						"create"
					]
				}
			},
			"response": []
		}
	]
}
排[mongoDB.postman_collection.json](https://github.com/user-attachments/files/17028382/mongoDB.postman_collection.json)
程
<img  width="100%" src="https://github.com/reve777/MongoDB/blob/main/Spring-MongoDB/src/main/resources/static/images/schedule.png" />
