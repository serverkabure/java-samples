package jp.canetrash.mongodb.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

public class SimpleSampleTest {
	/**
	 * DB取得
	 * 
	 * @return
	 * @throws Exception
	 */
	private DB getDB() throws Exception {
		MongoURI uri = new MongoURI("mongodb://192.168.128.3 ");
		Mongo mongo = new Mongo(uri);
		DB db = mongo.getDB("sengoku");
		return db;
	}

	/**
	 * データInsert
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInsert() throws Exception {
		DB db = getDB();
		// コレクションがなければコレクションを新規作成
		DBCollection col = db.getCollection("daimyou");
		for (int i = 0; i < 1000; i++) {
			col.insert(TestData.getRandomPersonalData());
		}
	}

	/**
	 * コレクションを削除
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDropCollection() throws Exception {
		DB db = getDB();
		// 存在しなくてもエラーにはならいない
		DBCollection col = db.getCollection("daimyou");
		col.insert(TestData.getRandomPersonalData());
		assertTrue(db.collectionExists("daimyou"));
		db.getCollection("daimyou").drop();
		assertFalse(db.collectionExists("daimyou"));
	}

	/**
	 * DBをドロップ
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDropDB() throws Exception {
		DB db = getDB();
		// 存在しなくてもエラーにはならいない
		db.dropDatabase();
	}

	/**
	 * 検索
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSimpleSearch() throws Exception {
		DB db = getDB();
		DBCollection col = db.getCollection("daimyou");

		// 全件取得
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			System.out.println(dbObject.get("name"));
			System.out.println(dbObject.get("age"));
			System.out.println((BasicDBList)dbObject.get("territory"));
		}
	}

	/**
	 * 条件付き検索
	 */
	@Test
	public void testSearchCondition() throws Exception {
		DB db = getDB();
		DBCollection col = db.getCollection("daimyou");
		// 条件付き取得
		// or 条件
		// territory = 美濃国 or territory = 飛騨国
		BasicDBObject query = new BasicDBObject();
		query.put("territory", "美濃国");
		query.put("territory", "飛騨国");
		System.out.println(query);

		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		// age > 45
		query = new BasicDBObject();
		query.put("age", new BasicDBObject("$gt", 45));
		System.out.println(query);
		cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		// weight in (40, 50, 60)
		query = new BasicDBObject();
		query.put("weight", new BasicDBObject("$in",
				new Integer[] { 40, 50, 60 }));
		System.out.println(query);
		cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		// 配列のマッチ。三河国、美濃国、出雲国をすべて要素にもつもの。
		query = new BasicDBObject();
		query.put("territory", new BasicDBObject("$all", new String[] { "三河国",
				"美濃国", "出雲国" }));
		System.out.println(query);
		cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		// territoryの要素の数が3つのもの
		query = new BasicDBObject();
		query.put("territory", new BasicDBObject("$size", 3));
		System.out.println(query);
		cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		// 名前でdistinct
		List distinct = col.distinct("name");
		for (Object obj : distinct) {
			System.out.println(obj);
		}
	}
}
