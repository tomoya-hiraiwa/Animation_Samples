package com.example.animationsample.list_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityTopicAndListBinding
import com.example.animationsample.databinding.SwipeSampleListItemBinding
import com.example.animationsample.databinding.TopicListItemBinding

class TopicAndList : AppCompatActivity() {
    private lateinit var b: ActivityTopicAndListBinding
    private var currentPosition = 0
    private val textData =
        listOf<String>(
            "Androidは、2003年にアンディ・ルービンらによって設立されたAndroid Inc.によって開発されました。2005年にGoogleがAndroid Inc.を買収し、オープンソースプロジェクトとしてAndroidを発展させました。最初の商用Androidデバイスは2008年に発売され、その後、Androidは急速に成長し、スマートフォン市場でのシェアを拡大しました。各バージョンのリリースごとに新機能と改善が追加され、Androidは世界中の開発者とユーザーにとって不可欠なプラットフォームとなりました。",
            "Androidのアーキテクチャは、大きく5つの層に分かれています。最下層はLinuxカーネルで、ハードウェア抽象化とセキュリティを提供します。その上に、各種ライブラリとAndroidランタイムがあり、アプリケーションの実行をサポートします。さらに、その上にはアプリケーションフレームワークがあり、開発者がアプリを構築するための基本的なツールやAPIを提供します。最上層にはユーザーアプリケーションが配置され、ユーザーが直接操作する部分となります。これらの層が連携することで、Androidの柔軟で強力な動作が実現されています。",
            "最新のAndroidバージョンでは、多くの新機能が追加されています。例えば、ダークモードのサポート、ジェスチャーナビゲーション、さらにはプライバシーとセキュリティの強化が挙げられます。また、人工知能と機械学習の統合が進み、ユーザーの使用パターンを学習してバッテリー寿命を延ばす「アダプティブバッテリー」や、アプリの使用状況に基づいてリソースを最適化する「アダプティブバッテリー」が導入されています。これらの新機能により、ユーザーエクスペリエンスが大幅に向上し、開発者もより高度なアプリケーションを作成できるようになっています。"
        )

    private val topicData =
        listOf<String>("1.Androidの歴史", "2.Androidのアーキテクチャ", "3.最新のAndroid機能")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTopicAndListBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            list.layoutManager = LinearLayoutManager(this@TopicAndList)
            list.adapter  = TopicListAdapter(textData)
            title.text = topicData[0]
            //set RecyclerView scroll listener
            list.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val manager = list.layoutManager as LinearLayoutManager
                //get show item position from layoutManager.
                val position = manager.findLastVisibleItemPosition()
                //if position is changed, change text value and start animation
                if (currentPosition != position){
                    currentPosition = position
                    changeTitle(position)
                }
            }
        }
    }
    //text change with alpha animation.
    private fun changeTitle(position: Int){
        b.apply {
            title.startAnimation(AlphaAnimation(1f,0f).apply {
                duration = 300
            })
            title.text = topicData[position]
            title.startAnimation(AlphaAnimation(0f,1f).apply {
                duration = 300
                startOffset = 300
            })
        }
    }
}

private class TopicListAdapter(private val dataList: List<String>): RecyclerView.Adapter<TopicListAdapter.TopicListViewHolder>(){
    inner class TopicListViewHolder(private val b: TopicListItemBinding): RecyclerView.ViewHolder(b.root){
        fun bindData(data: String){
            b.apply {
                text.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicListViewHolder {
        return TopicListViewHolder(TopicListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TopicListViewHolder, position: Int) {
        holder.bindData(data = dataList[position])
    }
}