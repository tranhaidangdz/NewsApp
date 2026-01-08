package com.loc.newsapp.util

object Constants {
    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY = "appEntry"
}
/*
file này đóng vai trò là một "Cuốn từ điển tên dùng chung" cho toàn bộ dự án.
Thay vì mỗi lần cần dùng đến một cái tên nào đó, bạn lại phải gõ tay (dễ bị nhầm lẫn, sai chính tả),
thì bạn định nghĩa nó một lần duy nhất tại đây.
1. object Constants
Tác dụng: Tạo ra một đối tượng duy nhất (Singleton) chứa các hằng số.
Giải thích: Bạn có thể gọi Constants.USER_SETTINGS ở bất cứ đâu trong dự án mà không cần phải tạo mới (khởi tạo) lại đối tượng này.
 Nó giống như một cái kho chứa các biển tên cố định.

 2. const val USER_SETTINGS = "userSettings"
Tên biến: USER_SETTINGS
Giá trị: "userSettings" (đây là tên thực tế của file lưu trên bộ nhớ máy).
Tác dụng: Đây là tên của file DataStore. Khi bạn khai báo preferencesDataStore(name = Constants.USER_SETTINGS), hệ thống sẽ tạo ra một file tên là userSettings.preferences_pb trong bộ nhớ điện thoại để lưu các cài đặt của người dùng.

3. const val APP_ENTRY = "appEntry"
Tên biến: APP_ENTRY
Giá trị: "appEntry"
Tác dụng: Đây là cái nhãn (Key) cho một giá trị cụ thể bên trong file. Giống như trong một cuốn sổ (USER_SETTINGS), bạn có nhiều mục khác nhau, thì appEntry là tiêu đề của mục dùng để đánh dấu việc "đã vào ứng dụng lần đầu chưa".
Tại sao lại phải viết như thế này? (Lợi ích
Hãy tưởng tượng nếu bạn không dùng Constants:
Ở file A, bạn gõ: "userSettings"
Ở file B, bạn gõ nhầm thành: "userSetting" (thiếu chữ s)
Kết quả: App sẽ tạo ra 2 file khác nhau, dẫn đến việc lưu dữ liệu ở chỗ này nhưng chỗ kia không đọc được. Lỗi này cực kỳ khó tìm (gọi là Hard-coded strings).
Khi dùng file Constants:
Nếu bạn muốn đổi tên file từ "userSettings" sang "my_app_data", bạn chỉ cần sửa duy nhất 1 chỗ tại file Constants này. Toàn bộ ứng dụng sẽ tự động cập nhật theo mà không sợ sai sót.
*/