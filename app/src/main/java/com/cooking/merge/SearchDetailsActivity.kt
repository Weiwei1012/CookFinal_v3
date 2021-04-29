package com.cooking.merge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.adapters.HotitemsAdapter
import com.cooking.merge.adapters.SearchitemsAdapter
import com.cooking.merge.models.FooditemsModel
import com.cooking.merge.models.SimpleFooditemsModel

class SearchDetailsActivity : AppCompatActivity() {

    //lateinit var adapter: HotitemsAdapter
    lateinit var adapter: SearchitemsAdapter
    lateinit var details_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var resultList: ArrayList<FooditemsModel>

    private val titles = arrayOf(
        "燕麥優格杯", "草莓蛋吐司", "培根蛋早餐燕麥粥", "全麥鮪魚蛋吐司", "法式吐司","蔥花蛋捲",
        "帕瑪森花椰菜煎餅", "酪梨豆腐青醬吐司", "五分鐘咖哩優格鮮蔬三明治","3分鐘微波法式吐司",
        "5分鐘舒芙蕾起司蛋", "太陽蛋吐司佐辣味焦糖奶油醬", "起士菇菇炒蛋盒子", "起司煎蛋", "蔥香味噌雞",
        "三杯菇菇雞", "安東燉雞", "醬燒雞腿杏鮑菇", "迷迭香黑胡椒馬鈴薯雞", "豉汁彩椒雞球","洋蔥雞丁",
        "泰式涼拌柚香雞絲", "豆芽雞絲", "酥炸雞皮", "啤酒燒雞腿", "蔥燒雞腿", "香滷棒棒腿","蜜汁雞翅",
        "蠔油雞翅", "美乃茄醬烤雞翅", "咖哩生薑燒雞腿", "咖哩優格烤雞翅", "青檸可樂雞翅腿","甜辣醬烤雞翅",
        "咖哩烤雞翅腿", "黑胡椒檸檬醬烤雞翅腿", "起司嫩雞塊", "咖哩美乃滋嫩雞塊","檸香鹽麴醬烤雞腿排",
        "嫩煎香料雞胸", "塔香味噌鹽麴烤雞腿排", "紅糟醬烤雞腿排","檸檬醋", "蔬果綠拿鐵", "金黃芝麻地瓜",
        "水果小冰棒", "微笑香蕉蛋糕", "蜂蜜檸檬梅漬小番茄", "法式蘋果克拉芙緹", "巧克力夏威夷豆餅乾", "髒髒派",
        "迷你優格牛奶司康","日式烏龍麵佐洋蔥燉雞", "XO醬炒泡麵", "酸辣泰式打拋豬拌炒泡麵", "絲瓜蛋麵線",
        "媽媽私房炒米粉", "肉絲炒麵", "雞絲燜米粉", "培根蛋奶義大利麵", "番茄雞蛋麵", "中日海陸烏龍乾麵",
        "家常肉末蛋米線", "冬日雨季小資男暖心湯麵", "越式涼拌米粉", "肉末冬粉", "照燒金針菇漢堡排", "蜂蜜醬燒五花肉",
        "蜂蜜芥末籽嫩煎豬排", "番茄燉肉", "韓式燒肉", "打拋豬", "櫛瓜炒肉末", "肉末蒸豆腐", "金針菇味噌肉燥",
        "古早味肉燥", "咖哩洋蔥炒肉片", "泰式風味炒肉片", "味噌野菜炒肉片", "薑汁豬肉", "泰式拌肉", "味噌炊飯",
        "和風白菜雞蛋丼", "鮭魚五穀米炒飯", "麻油菇菇雞飯", "空心菜梗炒飯", "海鮮泡飯", "豆腐飯", "日式豆腐丼",
        "泰式排骨湯泡飯", "沙茶豬肉燴飯", "高蛋白便當", "香芹皮芋粥", "麻婆豆腐", "醬燒嫩豆腐", "日式滑蛋豆腐",
        "可樂肉醬", "德式香腸炒洋芋佐芥末籽醬", "醬燒南瓜", "香料鹽洋芋", "馬鈴薯鮪魚煎餅", "蔬菜煎餅",
        "金針菇煎餅", "月見蔥爆豆芽菜蛋餅", "蔥油餅", "金沙春筍", "螞蟻上樹", "豆干肉絲", "紅燒豆干",
        "蔥蒜香炒豆皮", "醬燒豬肉豆皮捲", "肉末蒸蛋", "茄汁馬鈴薯", "奶油香煎馬鈴薯", "古早味碰皮白滷",
        "黑胡椒炒豆芽", "韓式雜菜", "咖哩炒三色", "醬油金針煮", "肉絲筍片", "櫻花蝦炒蘿蔔絲",
        "日式涼拌小黃瓜豆腐", "涼拌豆皮蔬菜", "涼拌干絲", "涼拌黑木耳", "涼拌菇菇醬", "菇菇蘿蔔雞湯",
        "金沙絲瓜湯", "海帶芽豆腐味噌湯", "鮮蔬豆腐味噌湯", "洋蔥湯", "麻油菇菇雞湯", "豚汁蔬菜湯",
        "蒜頭洋蔥雞湯", "韓式海帶芽排骨湯", "雞茸玉米羮")
    private val images = arrayOf(R.drawable.breakfast1, R.drawable.breakfast2,
        R.drawable.breakfast11, R.drawable.breakfast7, R.drawable.breakfast8, R.drawable.breakfast6,
        R.drawable.breakfast9, R.drawable.breakfast10, R.drawable.breakfast12, R.drawable.breakfast13,
        R.drawable.breakfast14, R.drawable.breakfast15, R.drawable.breakfast5, R.drawable.breakfast4,
        R.drawable.ch1, R.drawable.ch21,R.drawable.ch23, R.drawable.ch2, R.drawable.ch3, R.drawable.ch8,
        R.drawable.ch25, R.drawable.ch24, R.drawable.ch6,R.drawable.ch5, R.drawable.ch4, R.drawable.ch13,
        R.drawable.ch17, R.drawable.ch9, R.drawable.ch7, R.drawable.ch10, R.drawable.ch11, R.drawable.ch12,
        R.drawable.ch14, R.drawable.ch22, R.drawable.ch18, R.drawable.ch15, R.drawable.ch19, R.drawable.ch20,
        R.drawable.ch16, R.drawable.ch26, R.drawable.ch27, R.drawable.ch28, R.drawable.dessert2,
        R.drawable.dessert3, R.drawable.dessert4, R.drawable.dessert5, R.drawable.dessert6,
        R.drawable.dessert7, R.drawable.dessert8, R.drawable.dessert9, R.drawable.dessert10,
        R.drawable.dessert11, R.drawable.noodles2, R.drawable.noodles3, R.drawable.noodles4,
        R.drawable.noodles5, R.drawable.noodles6, R.drawable.noodles7, R.drawable.noodles8,
        R.drawable.noodles9, R.drawable.noodles10, R.drawable.noodles11, R.drawable.noodles12,
        R.drawable.noodles13, R.drawable.noodles14, R.drawable.noodles15, R.drawable.pork13,
        R.drawable.pork6, R.drawable.pork8, R.drawable.pork11, R.drawable.pork12, R.drawable.pork9,
        R.drawable.pork1, R.drawable.pork2, R.drawable.pork10, R.drawable.pork3, R.drawable.pork4,
        R.drawable.pork5, R.drawable.pork7, R.drawable.pork14, R.drawable.pork15,  R.drawable.rice4,
        R.drawable.rice5, R.drawable.rice6, R.drawable.rice7, R.drawable.rice8, R.drawable.rice9,
        R.drawable.rice10, R.drawable.rice11, R.drawable.rice12, R.drawable.rice13, R.drawable.rice15,
        R.drawable.rice17, R.drawable.sidedish1, R.drawable.sidedish8, R.drawable.sidedish27,
        R.drawable.sidedish2, R.drawable.sidedish7, R.drawable.sidedish10, R.drawable.sidedish11,
        R.drawable.sidedish22, R.drawable.sidedish23, R.drawable.sidedish24, R.drawable.sidedish25,
        R.drawable.sidedish12, R.drawable.sidedish13, R.drawable.sidedish16, R.drawable.sidedish17,
        R.drawable.sidedish18, R.drawable.sidedish15, R.drawable.sidedish19, R.drawable.sidedish20,
        R.drawable.sidedish14, R.drawable.sidedish29, R.drawable.sidedish31, R.drawable.sidedish32,
        R.drawable.sidedish33, R.drawable.sidedish26, R.drawable.sidedish21, R.drawable.sidedish3,
        R.drawable.sidedish5, R.drawable.sidedish28, R.drawable.sidedish30, R.drawable.sidedish6,
        R.drawable.sidedish9, R.drawable.sidedish4, R.drawable.soup1, R.drawable.soup2,
        R.drawable.soup3, R.drawable.soup4, R.drawable.soup5, R.drawable.soup6, R.drawable.soup7,
        R.drawable.soup8, R.drawable.soup9, R.drawable.soup10)
    private val ingredients = arrayOf("")
    private val sauces = arrayOf("")
    private val links = arrayOf("")
    private val keys = arrayOf("")
    private val favs = arrayOf("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_details)//製作搜尋結果的RecyclerView

        //////返回鈕//////
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle("搜尋結果")
        val search_results_items = getIntent().getStringArrayListExtra("passsearched")!!
        //////返回鈕//////

        details_rv = findViewById(R.id.details_rv)
        gridLayoutManager = GridLayoutManager(
            details_rv.context, 3,
            LinearLayoutManager.VERTICAL, false
        )
        details_rv.layoutManager = gridLayoutManager
        details_rv.setHasFixedSize(true)

        resultList = initialList()
        //adapter = HotitemsAdapter(resultList)      //之後要改成另一個adapter
        adapter = SearchitemsAdapter(resultList)

        details_rv.adapter = adapter

    }


    private fun initialList(): ArrayList<FooditemsModel> {

//        val search_results_items = getIntent().getStringArrayListExtra("passsearched")!!
//        val resultList = ArrayList<SimpleFooditemsModel>()
//        //將使用者所輸入的每一種食材所對應的料理array一一加入到resultList中並傳入Details_rv_adapter
//
//        for (i in search_results_items) {   //未來可能要用雙重迴圈，因為一開始所傳進來的是對應的料理”陣列“
//            resultList.add(SimpleFooditemsModel(R.drawable.ic_logo,i))                       //因此需再做一層回圈將所有品項分離出來
//        }
//        adapter = HotitemsAdapter(resultList)      //之後要改成另一個adapter
//        details_rv.adapter = adapter
        val allFoodList : ArrayList<FooditemsModel> = ArrayList()
        for (i in titles.indices){
            val model = FooditemsModel(images[i],titles[i],"蛋","醬油","http://www.google.com","0","0")
            allFoodList.add(model)
        }

        return allFoodList
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

