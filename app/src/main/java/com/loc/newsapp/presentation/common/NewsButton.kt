package com.loc.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//Tạo một nút bấm (Button) dùng chung cho toàn bộ app NewsApp( để trong pakage common: phổ biển là vì thế)
// Giúp tái sử dụng UI → không phải viết lại code tạo Button ở nhiều chỗ.
// Đảm bảo tính đồng bộ giao diện: tất cả nút đều có cùng màu nền, màu chữ, bo góc, style chữ.
// dễ bảo trì → nếu bạn muốn đổi màu nút, chỉ cần sửa 1 nơi.
@Composable
fun NewsButton(  //hàm compose xd nút này gồm 2 tham số
    text: String,  // nội dung chữ trên nút.
    onClick: () -> Unit  //hành động chạy khi người dùng bấm nút (callback function).
)
{

    Button(  //là composable có sẵn của Material3.
        onClick = onClick,  //khi bấm nút thì gọi hàm được truyền vào từ bên ngoài.
        colors = ButtonDefaults.buttonColors(  //ButtonDefaults.buttonColors: Bộ màu mặc định chuyên dành cho Button.
            containerColor = MaterialTheme.colorScheme.primary,  //containerColor: màu nền của nút → được lấy từ MaterialTheme.colorScheme.primary (màu chủ đạo của app).
            contentColor = White  //contentColor: màu cho nội dung bên trong (chữ hoặc icon) → đặt là White.
        ),
        shape = RoundedCornerShape(size = 6.dp)  //Tạo nút bo góc 6dp
    ) {
        Text(  //Nội dung Text bên trong Button
            text = text,  //hiển thị chính xác nội dung được truyền vào khi gọi NewsButton(tham số text đc truyền khi ngta gọi hàm NewsButton)
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)  //MaterialTheme.typography.labelMedium → dùng style chữ cỡ nhỏ theo bộ Typography của app;
        // copy(fontWeight = FontWeight.SemiBold)→ chỉnh lại weight để chữ in đậm vừa.
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
)
{
    TextButton(onClick = onClick){
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold,
            color = Color.White
            )
        )
    }
}
