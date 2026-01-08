package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow
//Trong lập trình Android (đặc biệt là với kiến trúc Clean Architecture),
// Interface LocalUserManager này thường được dùng để quản lý Trạng thái lần đầu sử dụng ứng dụng (Onboarding state).
//Nó giúp ứng dụng ghi nhớ việc người dùng đã xem qua các trang giới thiệu (Onboarding) hay chưa, để lần sau mở app,
// họ sẽ vào thẳng màn hình chính (Home) thay vì phải xem lại từ đầu.
interface LocalUserManager {
    suspend fun saveAppEntry()  //Hàm này dùng để Ghi dữ liệu.
                                //    Chức năng: Khi người dùng nhấn nút "Get Started" ở trang Onboarding cuối cùng, bạn gọi hàm này để lưu một "dấu mốc" vào bộ nhớ máy (thường là dùng DataStore hoặc SharedPreferences).
                                //    Tại sao là suspend? Vì việc ghi dữ liệu vào bộ nhớ có thể tốn thời gian (I/O), nên cần chạy trong một Coroutine để không làm treo giao diện (UI).
                                //    Ý nghĩa: "Đánh dấu rằng người dùng này đã hoàn thành phần giới thiệu".
    fun readAppEntry(): Flow<Boolean>  // Hàm này dùng để Đọc dữ liệu.
                                        //    Chức năng: Trả về một luồng dữ liệu (Flow) kiểu Boolean.
                                        //    true: Người dùng đã xem giới thiệu rồi.
                                        //    false: Người dùng mới, chưa xem giới thiệu.
                                        //    Tại sao dùng Flow? Để ứng dụng có thể "lắng nghe" sự thay đổi của dữ liệu theo thời gian thực. Ngay khi bạn gọi saveAppEntry(), giá trị trong Flow này sẽ tự động cập nhật từ false thành true.
                                        //    Ứng dụng thực tế: Thường được dùng ở MainActivity hoặc ViewModel để quyết định xem nên hiện màn hình nào khi vừa mở App:
                                        //    Nếu readAppEntry là false -> Hiển thị OnBoardingScreen.
                                        //    Nếu readAppEntry là true -> Hiển thị HomeScreen.
/*
Bước 1: Khi người dùng mở ứng dụng lần đầu, hàm readAppEntry() được gọi và trả về giá trị false. Lúc này, ứng dụng sẽ tự động hiển thị màn hình Onboarding để hướng dẫn người dùng.
Bước 2: Trong quá trình người dùng vuốt qua các trang giới thiệu, trạng thái trong bộ nhớ vẫn được giữ là false vì người dùng chưa hoàn thành toàn bộ quy trình.
Bước 3: Khi người dùng nhấn nút "Get Started" ở trang cuối cùng, hàm saveAppEntry() sẽ được thực thi để lưu giá trị true vào bộ nhớ máy, đánh dấu đã hoàn thành giới thiệu.
Bước 4: Từ các lần mở ứng dụng tiếp theo, hàm readAppEntry() luôn trả về giá trị true. Ứng dụng sẽ nhận diện được đây là người dùng cũ và điều hướng thẳng vào màn hình Home mà không hiện lại Onboarding nữa.
*/
}

