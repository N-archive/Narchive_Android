package com.chunbae.narchive.presentation.ui.detail.book

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityBookMovieDetailBinding
import com.chunbae.narchive.presentation.ui.detail.book.adapter.DetailBookKeywordAdapter

class DetailBookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookMovieDetailBinding
    private var isLoading = false
    private var isThumbnail = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_detail)

        initBinding()
        initView()
    }

    private fun initBinding() {
        binding.bookActivity = this
        binding.type = "Book"
        binding.bookData = returnBookData()
    }

    private fun initView() {
        binding.detailBookMovieRvKeyword.adapter = DetailBookKeywordAdapter(returnBookData().keywords)
    }

    fun bookCard() : View = binding.detailBookMovieLayoutThumbnail

    fun onImageCardClick(view : View) {
            if (!isLoading) {
                isLoading = true
                val anim = android.animation.ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
                    .setDuration(400)
                anim.doOnEnd { on ->
                    rotateMore(view)
                    isLoading = false
                }
                anim.start()

            }
        }

    private fun rotateMore(view: View) {
        if(isThumbnail) {
            binding.detailBookMovieIvThumbnail.visibility = View.INVISIBLE
            binding.detailBookMovieIvUserImage.visibility = View.VISIBLE
        } else {
            binding.detailBookMovieIvThumbnail.visibility = View.VISIBLE
            binding.detailBookMovieIvUserImage.visibility = View.INVISIBLE
        }
        isThumbnail = !isThumbnail
        android.animation.ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            .setDuration(400).start()
    }

    /** Dummy */
    private fun returnBookData() : BookData = BookData(0, "123", R.drawable.ic_launcher_foreground, "1 번째 책", "춘배", "춘배사", "2023.01.01", 1.0F, listOf("1", "2", "3"), "처음 추천받았을 때는 표지때문에 크게 기대를 하고 본 책은 아니었다.\n" +
            "하지만 읽으면 읽을수록 어느샌가 빠져들게 되었다.\n" +
            "특히 주인공이 자신의 어중간한 정체성때문에 혼란하는 모습은 마치\n" +
            "과연 개발자라는 길을 계속 걸어가는게 맞는지 혼란을 겪었던 내 모습과 오버랩되어 더 빠져들게 되었던 것 같다.\n" +
            "이 책을 읽으면서 나도 계속해서 나만의 길을 걸어나가야겠다는 의지를 다시 한번 불태울 수 있었던 좋은 책인 것 같다.\n" +
            "언젠가 시간이 난다면 꼭 다시 한번 읽어보고 싶은 책이다.")
}