package com.loc.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.Dimens.PageIndicatorWidth
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import com.loc.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        //rememberPagerState:  Tạo và ghi nhớ trạng thái của HorizontalPager , Không bị reset khi UI recomposition
        //Các tham số:
        //initialPage = 0: Trang onboarding đầu tiên (index = 0)
        //{ pages.size } : Tổng số trang
        //pages thường là List<OnBoardingPageData>

        //👉 pagerState dùng để:
        //Biết đang ở trang nào
        //Điều khiển chuyển trang (animateScrollToPage())

        val buttonState =
            remember {  //remember : Giữ giá trị buttonState trong suốt vòng đời composable
                derivedStateOf {  ////derivedStateOf: 1 State phụ thuộc từ state khác ; buttonState phụ thuộc vào: pagerState.currentPage(Chỉ recompose khi currentPage thay đổi Giúp tối ưu hiệu năng)
                    when (pagerState.currentPage) {  //when (pagerState.currentPage)  => Dựa vào trang hiện tại, quyết định nội dung nút:
                        0 -> listOf(
                            "",
                            "Next"
                        )  // 0-> : trang 0,1,2... thì nút trai rỗng nút phải có ND là "next"
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                        //listOf("Back", "Next")
                        //Index 0 → nút trái
                        //Index 1 → nút phải
                    }
                }
            }

        HorizontalPager(  //HorizontalPager : Pager trượt ngang Tương tự ViewPager cũ ; Thuộc thư viện: androidx.compose.foundation.pager
            state = pagerState  //Gán trạng thái pager , Cho phép:
            //Lấy trang hiện tại
            //Scroll bằng code
        ) {
            //Lambda { index -> }
            //index = vị trí trang hiện tại
            //Pager sẽ gọi lambda OnboardingPage này cho mỗi page
                index ->
            OnBoardingPage(page = pages[index])  //Render UI cho từng trang onboarding
            //pages[index] chứa:
            //title
            //description
            //image
            //animation (Lottie)
            //👉 Mỗi lần swipe:
            //index thay đổi
            //Trang mới được vẽ lại
            /*
            5️⃣ Luồng hoạt động tổng thể 🔄
            App mở → initialPage = 0
            HorizontalPager hiển thị trang 0
            pagerState.currentPage = 0
            buttonState → ["", "Next"]
            Người dùng swipe → sang page 1
            currentPage đổi → derivedStateOf chạy lại
            Button đổi thành ["Back", "Next"]
            */
        }
        Spacer(modifier = Modifier.weight(1f))  //đẩy xuống hàng dưới cùng của app, ở đây ta muốn row nằm sát lề dưới màn hình
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MediumPadding2) //kich thuoc cho vùng đệm(đã khai báo trong file dimens)
                .navigationBarsPadding(),  // thêm padding tự động cho các bottom bar

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                val scope = rememberCoroutineScope()  // goi bất đồng bộ

                if (buttonState.value[0].isNotEmpty())  //Nếu buttonState.value[0] không rỗng(tức là có ND bên trái ko rỗng, VD = "back" )
                {
                    NewsTextButton(
                        text = buttonState.value[0],  //gán chữ trên nút là buttonState.value[0] => gán chữ "báck" lên nút
                        onClick = {  //khi bấm vào nút đó thì thay đổi trạng thái =  lanch
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)  //pagerState.currentPage -1 : sang trang trước(quay lại trang trc đó khi bấm back), gọi hiệu ứng animateScrollToPage để chuyển giữa các trang
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonState.value[1],  //gtri bên phải cua nút buttonState. có thể là "navigateToHome " hoặc "next"
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {
                                //TODO: Navigate to Home Screen: nếu gtri bấm trên pagerState = 3 tức là "navigateToHome"
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1  // nếu gtri bấm là "next" thì ta chuyển tranh mới = currentPage + 1
                                )
                            }
                        }
                    }
                )

            }

        }
        Spacer(modifier = Modifier.weight(0.5f))  //tăng kcach so với lề dưới cùng 
    }
}