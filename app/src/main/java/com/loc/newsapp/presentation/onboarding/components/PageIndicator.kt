package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.presentation.Dimens.IndicatorSize

@Composable
fun PageIndicator(  //PageIndicator là một composable dùng để:✅ Tạo các dấu chấm hiển thị trang hiện tại trong Onboarding giống như ● ○ ○;    ● là trang đang được chọn (selectedPage) ○ là những trang khác
    modifier: Modifier = Modifier,  //cho phép truyền modifier từ ngoài vào (padding, alignment,...).
    pageSize: Int,  //tổng số trang (bao nhiêu dấu chấm?).
    selectedPage: Int, //trang hiện tại (trang đang được highlight).
    selectedColor: Color = MaterialTheme.colorScheme.primary,  //màu dấu chấm khi được chọn (mặc định là màu chủ đạo của theme).
    unselectedColor: Color  //màu dấu chấm khi không được chọn.
) {

    Row(  //sắp xếp các phần tử nằm cạnh nhau theo chiều ngang.
        modifier = modifier,  //nhận modifier từ ngoài ơ đây là nhận từ tham số modifier của hàm PageIndicator
        horizontalArrangement = Arrangement.SpaceBetween  // tạo khoảng cách đều giữa các dấu chấm.
    ) {
        repeat(times = pageSize) { page ->  //repeat(pageSize) → lặp pageSize lần.  Mỗi lần lặp → tạo 1 dấu chấm. page là chỉ số trang (0, 1, 2, ...).
            Box(  //Tạo dấu chấm bằng Box
                modifier = Modifier.size(IndicatorSize)  //Kích thước dấu chấm
                    .clip(CircleShape)  //Bo tròn thành hình tròn
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)  //Đổ màu nền:Nếu page đang lặp đúng bằng selectedPage → màu được chọn.Ngược lại → màu chưa chọn.
            )
        }
    }
}
