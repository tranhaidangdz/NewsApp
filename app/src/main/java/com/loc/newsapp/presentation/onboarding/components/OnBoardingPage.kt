package com.loc.newsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.onboarding.Page
import com.loc.newsapp.presentation.onboarding.pages
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,  //modifier: Modifier = Modifier: cho phép truyền modifier từ bên ngoài (vd: padding, fillMaxSize, background…). Nếu không truyền thì dùng Modifier mặc định.
    page: Page  //page: Page: một object chứa dữ liệu của trang (ảnh, tiêu đề, mô tả).
){

    Column( modifier = modifier) {  //Column:sắp xếp các phần tử theo chiều dọc. modifier = modifier: nhận modifier từ bên ngoài (ở đây nó nhận từ column truyền xuống ).
        Image(
            modifier = Modifier
                .fillMaxWidth()  //ảnh chiếm toàn bộ chiều ngang.
                .fillMaxHeight(0.6f),  //ảnh chiếm 60% chiều cao của toàn màn hình.
            painter = painterResource(id = page.image),  //lấy ảnh từ tài nguyên drawable dựa trên ID trong page.image.
            contentDescription = null, //không cần mô tả (phục vụ accessibility).
            contentScale = ContentScale.Crop  //ảnh phóng to & cắt sao cho đầy khung, không bị méo.
        )
        Spacer(modifier = Modifier.height(MediumPadding1))  //Tạo một khoảng cách có chiều cao = MediumPadding1.
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2), //chừa khoảng 2 bên.
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold), //chỉnh sửa style: đặt chữ đậm.
            color = colorResource(R.color.display_small)  //lấy màu từ file màu XML.
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)  //hiển thị ở light mode.
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)  //hiển thị ở dark mode nhờ uiMode = UI_MODE_NIGHT_YES.
@Composable
fun OnBoardingPagePreview(){
    NewsAppTheme { //áp dụng theme của ứng dụng (màu sắc, font…).
        OnBoardingPage(  //dùng trang onboarding đầu tiên trong danh sách pages.
            page = pages[0]
        )
    }
}
