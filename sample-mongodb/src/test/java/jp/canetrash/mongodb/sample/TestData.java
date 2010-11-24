package jp.canetrash.mongodb.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * TestData
 * 
 * @author tfunato
 * 
 */
public class TestData {

	private static Random rnd = new Random();
	private static List<String> pref = new ArrayList<String>();
	private static List<String> name = new ArrayList<String>();

	private TestData() {
	}

	/**
	 * {name: "xxx", sex : "male or female", age: 33, height : "xxx , weight :
	 * xxx, territory:['a','b',...]}
	 * 
	 * @return
	 */
	public static DBObject getRandomPersonalData() {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("name", getName());
		dbObject.put("sex", "male");
		dbObject.put("age", getAge());
		dbObject.put("height", getHeight());
		dbObject.put("weight", getWeight());

		dbObject.put("territory", getTerritory());
		return dbObject;
	}

	private static List<String> getTerritory() {
		List<String> list = new ArrayList<String>();
		int cnt = nextInt() % 8 + 1;
		for (int i = 0; i < cnt; i++) {
			list.add(pref.get(nextInt() % pref.size()));
		}
		return list;
	}

	private static Integer getWeight() {
		return nextInt() % 20 + 50;
	}

	private static Integer getHeight() {
		return nextInt() % 60 + 120;
	}

	private static String getName() {
		return name.get(nextInt() % name.size());
	}

	private static Integer getAge() {
		return nextInt() % 30 + 20;
	}

	private static int nextInt() {
		return Math.abs(rnd.nextInt());
	}

	static {
		name.add("青山忠俊");
		name.add("青山忠成");
		name.add("秋月種長");
		name.add("朝倉貞景");
		name.add("朝倉孝景");
		name.add("朝倉義景");
		name.add("浅野長晟");
		name.add("足利義昭");
		name.add("足利義稙");
		name.add("足利義輝");
		name.add("足利義晴");
		name.add("足利義尚");
		name.add("尼子経久");
		name.add("尼子晴久");
		name.add("有馬豊氏");
		name.add("井伊直孝");
		name.add("井伊直政");
		name.add("池田輝政");
		name.add("伊東祐兵");
		name.add("上杉景勝");
		name.add("上杉謙信");
		name.add("上井覚兼");
		name.add("大内義隆");
		name.add("大友宗麟");
		name.add("小笠原貞慶");
		name.add("小笠原忠真");
		name.add("小笠原長時");
		name.add("小笠原秀政");
		name.add("織田信長");
		name.add("小野寺義道");
		name.add("加藤清正");
		name.add("亀井政矩");
		name.add("吉川広家");
		name.add("栗山大膳");
		name.add("黒田長政");
		name.add("黒田孝高");
		name.add("後藤又兵衛");
		name.add("斉藤義龍");
		name.add("酒井忠勝");
		name.add("酒井忠世");
		name.add("相良義陽");
		name.add("相良頼房");
		name.add("佐竹義重");
		name.add("佐竹義宣");
		name.add("佐々成政");
		name.add("島左近	天");
		name.add("島津家久");
		name.add("島津忠長");
		name.add("島津忠良");
		name.add("島津以久");
		name.add("島津義弘");
		name.add("多賀谷重経");
		name.add("武田信玄");
		name.add("武田信虎");
		name.add("武田信吉");
		name.add("竹中重治");
		name.add("立花道雪");
		name.add("立花直次");
		name.add("立花宗茂");
		name.add("伊達秀宗");
		name.add("伊達政宗");
		name.add("津軽為信");
		name.add("筒井定次");
		name.add("筒井順慶");
		name.add("筒井順興");
		name.add("筒井順昭");
		name.add("筒井順盛");
		name.add("土井利勝");
		name.add("藤堂高虎");
		name.add("藤堂高吉");
		name.add("徳川家康");
		name.add("徳川信康");
		name.add("徳川秀忠");
		name.add("徳川義直");
		name.add("豊臣秀吉");
		name.add("豊臣秀頼");
		name.add("鍋島勝茂");
		name.add("鍋島直茂");
		name.add("南部信直");
		name.add("丹羽長重");
		name.add("丹羽長秀");
		name.add("蜂須賀至鎮");
		name.add("保科正貞");
		name.add("細川忠興");
		name.add("細川忠利");
		name.add("細川藤孝");
		name.add("前田利家");
		name.add("前田利常");
		name.add("前田利長");
		name.add("松井康之");
		name.add("松平清康");
		name.add("松平定綱");
		name.add("松平忠輝");
		name.add("松平忠直");
		name.add("松平信綱");
		name.add("松平信康");
		name.add("松平広忠");
		name.add("松永久通");
		name.add("松前慶広");
		name.add("松浦隆信");
		name.add("水野勝成");
		name.add("三好長慶");
		name.add("毛利輝元");
		name.add("毛利秀就");
		name.add("毛利秀元");
		name.add("毛利元就");
		name.add("最上義光");
		name.add("山中鹿介");
		name.add("結城晴朝");
		name.add("結城秀康");
		name.add("龍造寺隆信");
		name.add("脇坂安元");

		pref.add("伊賀国");
		pref.add("伊勢国");
		pref.add("志摩国");
		pref.add("尾張国");
		pref.add("三河国");
		pref.add("遠江国");
		pref.add("駿河国");
		pref.add("伊豆国");
		pref.add("甲斐国");
		pref.add("相模国");
		pref.add("武蔵国");
		pref.add("安房国");
		pref.add("上総国");
		pref.add("下総国");
		pref.add("常陸国");
		pref.add("東山道");
		pref.add("近江国");
		pref.add("美濃国");
		pref.add("飛騨国");
		pref.add("信濃国");
		pref.add("諏方国");
		pref.add("上野国");
		pref.add("下野国");
		pref.add("陸奥国");
		pref.add("石背国");
		pref.add("岩代国");
		pref.add("石城国");
		pref.add("磐城国");
		pref.add("陸前国");
		pref.add("陸中国");
		pref.add("陸奥国");
		pref.add("出羽国");
		pref.add("羽前国");
		pref.add("羽後国");
		pref.add("北陸道");
		pref.add("若狭国");
		pref.add("越前国");
		pref.add("加賀国");
		pref.add("能登国");
		pref.add("越中国");
		pref.add("越後国");
		pref.add("佐渡国");
		pref.add("山陰道");
		pref.add("丹波国");
		pref.add("丹後国");
		pref.add("但馬国");
		pref.add("因幡国");
		pref.add("伯耆国");
		pref.add("出雲国");
		pref.add("石見国");
		pref.add("隠岐国");
		pref.add("山陽道");
		pref.add("播磨国");
		pref.add("美作国");
		pref.add("備前国");
		pref.add("備中国");
		pref.add("備後国");
		pref.add("安芸国");
		pref.add("周防国");
		pref.add("長門国");
		pref.add("南海道");
		pref.add("紀伊国");
		pref.add("淡路国");
		pref.add("阿波国");
		pref.add("讃岐国");
		pref.add("伊予国");
		pref.add("土佐国");
		pref.add("西海道");
		pref.add("豊前国");
		pref.add("豊後国");
		pref.add("筑前国");
		pref.add("筑後国");
		pref.add("肥前国");
		pref.add("肥後国");
		pref.add("日向国");
		pref.add("大隅国");
		pref.add("大隅国");
		pref.add("多禰国");
		pref.add("薩摩国");
		pref.add("壱岐国");
		pref.add("対馬国");
		pref.add("琉球国");
	}
}
