package com.loc.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
}
/*
DI (Dependency Injection - Tiêm phụ thuộc) giúp tiêm 1 đối tương vào 1 lớp nào đó
giống như một "dịch vụ giao đồ ăn". Thay vì bạn phải tự vào bếp nấu ăn (tự khởi tạo đối tượng), bạn chỉ cần đặt món và "shipper" (DI Framework) sẽ mang món ăn đến tận bàn cho bạn.
Dưới đây là phân tích chi tiết về DI và hai "shipper" nổi tiếng nhất hiện nay: Dagger Hilt và Koin.

1. DI (Dependency Injection) là gì?
DI là một kỹ thuật thiết kế phần mềm, nơi một đối tượng không tự tạo ra các đối tượng mà nó phụ thuộc vào.
Vấn đề: Nếu lớp OnBoardingViewModel tự tạo LocalUserManagerImpl, thì khi bạn muốn thay đổi cách lưu trữ, bạn phải sửa code ở rất nhiều nơi.
Giải pháp: Bạn "tiêm" LocalUserManager thông qua Constructor. Điều này giúp code linh hoạt, dễ kiểm tra (Unit Test) và các lớp không bị dính chặt vào nhau.
2. Dagger Hilt (Vua của sự chuyên nghiệp)
Hilt được xây dựng dựa trên Dagger2 (do Google phát triển). Đây là thư viện Compile-time, nghĩa là nó kiểm tra mọi lỗi kết nối giữa các đối tượng ngay khi bạn bấm nút "Build" ứng dụng.
Cơ chế: Nó tự động tạo ra một lượng lớn code Java/Kotlin ẩn bên dưới để kết nối các thành phần.

Ưu điểm:
Hiệu suất cực cao: Vì mọi thứ đã được chuẩn bị sẵn từ lúc build app.
Tính an toàn: Nếu bạn quên khai báo một đối tượng, app sẽ báo lỗi ngay khi biên dịch, không đợi đến lúc chạy mới crash.
Được Google hỗ trợ: Tích hợp cực tốt với ViewModel, WorkManager, Jetpack Compose.
Nhược điểm: Học hơi khó, cấu hình ban đầu hơi rườm rà (cần nhiều Annotation như @HiltAndroidApp, @Inject, @Module).

3. Koin (Vua của sự đơn giản)
Koin là một framework thuần Kotlin, hoạt động theo cơ chế Runtime (Service Locator).
Cơ chế: Koin không tạo code ẩn. Nó giữ một danh sách các đối tượng trong bộ nhớ và khi bạn cần (bằng từ khóa by inject()), nó sẽ đi tìm trong danh sách đó để đưa cho bạn.

Ưu điểm:
Dễ học kinh khủng: Chỉ mất 5-10 phút là bạn có thể sử dụng được.
Cấu hình cực nhanh: Không cần Annotation phức tạp, chỉ cần khai báo trong một module { ... }.
Ngôn ngữ Kotlin: Viết code rất "mượt" và đúng phong cách Kotlin.

Nhược điểm:
Lỗi Runtime: Nếu bạn quên khai báo một đối tượng, app vẫn Build thành công, nhưng khi mở màn hình đó lên thì app mới bị Crash.
Hiệu suất: Chậm hơn Hilt một chút (nhưng với app nhỏ và vừa thì không đáng kể).
==============================================================================================
1. Sự phụ thuộc (Dependency) là gì?
Trong lập trình hướng đối tượng, một lớp (Class A) thường cần một lớp khác (Class B) để thực hiện công việc của nó. Lúc này, ta nói A phụ thuộc vào B.
Cách làm thông thường (Không có DI): Lớp A sẽ tự khởi tạo lớp B bên trong thân của nó bằng từ khóa new (hoặc khởi tạo trực tiếp trong Kotlin).
Hệ quả: A và B bị "dính chặt" vào nhau (Hard-dependency). Nếu bạn muốn thay đổi B, bạn buộc phải sửa code của A.

2. Đảo ngược điều khiển (Inversion of Control - IoC)
Đây là nguyên lý gốc rễ của DI. Thay vì lớp A nắm quyền kiểm soát việc "khi nào tạo ra B", lớp A sẽ từ bỏ quyền đó. Nó chỉ tuyên bố: "Tôi cần một thứ giống như B để hoạt động, ai đó hãy đưa nó cho tôi".
Bản chất: Quyền kiểm soát việc khởi tạo đối tượng được chuyển từ bên trong lớp đó ra bên ngoài (cho một khung sườn hoặc hệ thống quản lý).

3. "Tiêm" (Injection) là gì?
"Tiêm" chính là hành động cung cấp sự phụ thuộc từ bên ngoài vào bên trong lớp thông qua một "đường dẫn". Có 3 đường dẫn phổ biến:
Constructor Injection: Đưa vào qua hàm khởi tạo (Cách phổ biến nhất trong Android).
Field Injection: Đưa thẳng vào biến (thường thấy với @Inject trong Hilt).
Method Injection: Đưa vào qua một hàm setter.
Tại sao bản chất này lại quan trọng?
Nếu không có DI, code của bạn giống như một chuỗi mắt xích bị hàn chết vào nhau. Nếu một mắt xích hỏng hoặc cần thay thế, bạn phải cắt bỏ toàn bộ sợi dây.
=============================================================================================
CÁC ANOTATION(CHÚ THÍCH)  CỦA THƯ VIÊN DAGGER HILT : @HiltAndroidApp, @Inject, @Module
Để hiểu các Annotation này, bạn hãy tưởng tượng Dagger Hilt giống như một hệ thống quản lý kho tự động.
Các Annotation chính là những "lệnh chỉ dẫn" để hệ thống này biết cách vận hành.

Dưới đây là ý nghĩa chi tiết của từng loại:

1. @HiltAndroidApp (Nút nguồn hệ thống)

Vị trí: Đặt phía trên lớp Application của bạn.
Bản chất: Đây là Annotation quan trọng nhất để kích hoạt Hilt. Nó biến lớp Application của bạn thành một "Container" (thùng chứa) gốc.
Tác dụng: Khi bạn thêm nó, Hilt sẽ tự động tạo ra các thành phần cần thiết để quản lý vòng đời của các đối tượng trong toàn bộ ứng dụng.

Lưu ý: Nếu thiếu cái này, mọi Annotation khác của Hilt (như @Inject) đều vô dụng.

2. @Inject (Lệnh yêu cầu và Lệnh bàn giao)
Annotation này có "hai mặt" tùy vào nơi bạn đặt nó:
Tại Constructor (Hàm khởi tạo): Nó nói với Hilt rằng: "Đây là cách tạo ra tôi, hãy đưa tôi vào danh sách hàng hóa trong kho".
Ví dụ: @Inject constructor(manager: LocalUserManager) giúp Hilt biết cách tạo ra Use Case đó.

Tại Field (Biến trong Activity/Fragment): Nó nói với Hilt rằng: "Tôi đang cần thứ này, hãy lấy từ trong kho ra và gắn vào đây cho tôi".
Ví dụ: @Inject lateinit var saveAppEntry: SaveAppEntry.

3. @Module (Hướng dẫn sản xuất)
Vị trí: Đặt phía trên một object hoặc class.
Bản chất: Không phải lúc nào Hilt cũng biết cách tạo ra một đối tượng (đặc biệt là các đối tượng từ thư viện bên ngoài hoặc các Interface).
Tác dụng: Module là nơi bạn viết các hàm để "dạy" Hilt cách khởi tạo những thứ phức tạp.
Đi kèm với @InstallIn: @Module thường đi đôi với @InstallIn. Nó xác định "tuổi thọ" của các đối tượng trong đó.
Ví dụ: @InstallIn(SingletonComponent::class) nghĩa là các đối tượng này sẽ sống suốt đời cùng ứng dụng.

Mối quan hệ thực tế trong dự án của bạn:
Hãy xem các Annotation này phối hợp với nhau như thế nào để "tiêm" LocalUserManagerImpl vào app:

@HiltAndroidApp: Bật công tắc hệ thống DI cho toàn bộ dự án NewsApp.

@Module: Bạn tạo một Module để nói với Hilt: "Này, mỗi khi có ai cần LocalUserManager (interface), hãy đưa cho họ bản thực thi là LocalUserManagerImpl nhé".

@Inject:

Trong SaveAppEntry, bạn dùng @Inject ở constructor để Hilt biết cách tạo Use Case này.

Trong ViewModel, bạn dùng @Inject để Hilt tự động đưa Use Case đó vào mà bạn không cần dùng từ khóa new hay khởi tạo thủ công.
Tóm tắt bằng hình ảnh ẩn dụ:
@HiltAndroidApp: Mở cửa công ty vận chuyển.
@Module: Sách hướng dẫn lắp ráp các linh kiện phức tạp.
@Inject: Shipper giao linh kiện đến tận nơi bạn cần.
*/