package com.loc.newsapp.domain.usecase

import com.loc.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
//Lớp ReadAppEntry này là "người anh em" song sinh với SaveAppEntry.
// Nhiệm vụ của nó là lấy dữ liệu từ kho lưu trữ ra để ứng dụng biết nên hiện màn hình nào (Onboarding hay Home).
//Tuy nhiên, trong đoạn code bạn gửi, có một chi tiết cần lưu ý về mặt kỹ thuật (lỗi nhỏ thường gặp). Hãy cùng phân tích nhé:
//1. Phân tích cấu trúc
//Mục đích: Lấy trạng thái "đã vào app" dưới dạng một luồng dữ liệu liên tục (Flow).
//Sự khác biệt với SaveAppEntry: SaveAppEntry là một hành động (ghi vào), còn ReadAppEntry là một yêu cầu cung cấp thông tin (lấy ra).
class ReadAppEntry (
    private val localUserManger: LocalUserManager
)
    {
        suspend operator fun invoke(): Flow<Boolean> {
            return localUserManger.readAppEntry()
        }
        /*
        Lỗi nhỏ: Thông thường, chúng ta không nên dùng từ khóa suspend cho hàm trả về một Flow.
        Tại sao? Flow bản chất nó là một dòng chảy dữ liệu lạnh (cold stream). Việc khởi tạo cái "vòi nước" này rất nhanh,
        nó không tốn thời gian như việc ghi dữ liệu. Chỉ khi nào bạn bắt đầu "hứng nước" (gọi .collect()), lúc đó mới cần đến môi trường Coroutine.
        Cách sửa: Bạn nên bỏ suspend đi để ViewModel có thể gọi nó dễ dàng hơn.

        operator fun invoke(): Vẫn giữ tác dụng như cũ. Khi bạn muốn đọc dữ liệu, trong ViewModel bạn chỉ cần gọi:
        readAppEntry() thay vì readAppEntry.invoke().
        */
    }
