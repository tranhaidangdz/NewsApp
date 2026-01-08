package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {  //private val context: Context(khai báo 1 biến dùng trong clas này
//    Để làm việc với DataStore (lưu file vào bộ nhớ điện thoại), chúng ta bắt buộc phải có Context. Bạn có thể coi Context như một "giấy phép" hoặc "chìa khóa" mà Android cấp cho ứng dụng để truy cập vào hệ thống file.
//    private val: Nghĩa là cái chìa khóa này chỉ được dùng nội bộ bên trong lớp này thôi, không ai bên ngoài được phép mượn nó.

    // : LocalUserManager
//Kế thừa/Thực thi: Dấu : có nghĩa là lớp này cam kết sẽ thực hiện đầy đủ các hàm đã khai báo trong interface LocalUserManager (là 2 hàm saveAppEntry và readAppEntry mà chúng ta vừa nói ở trên).
//Mục đích: Việc này giúp code của bạn linh hoạt. Sau này nếu bạn không muốn dùng DataStore nữa mà muốn dùng Database (Room),
// bạn chỉ cần tạo một lớp mới cũng "kế thừa" LocalUserManager mà không cần sửa lại code ở các tầng trên.

    override suspend fun saveAppEntry() {  //Hàm ghi dữ liệu: saveAppEntry() lúc này ta kế thừa từ interface và ghi đè override code :Tác dụng: Thực hiện việc "đóng dấu".
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
        //Giải thích: Khi người dùng bấm "Get Started", hàm này sẽ mở "cuốn sổ" dataStore ra, tìm đúng mục APP_ENTRY và ghi đè giá trị true vào đó.
        //Từ khóa suspend: Vì việc ghi vào bộ nhớ máy có thể mất một chút thời gian, nên nó phải là hàm tạm dừng (suspend) để không làm đơ màn hình của người dùng.
    }

    override fun readAppEntry(): Flow<Boolean> { //Hàm đọc dữ liệu: readAppEntry()
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKey.APP_ENTRY] ?: false
        }
        //Tác dụng: Kiểm tra xem người dùng đã "đóng dấu" chưa.
        //Giải thích: Hàm này liên tục theo dõi "cuốn sổ" dữ liệu.
        //Nếu tìm thấy mục APP_ENTRY, nó sẽ trả về giá trị đang lưu (ví dụ: true).
        //Nếu không tìm thấy (lần đầu mở app), dấu ?: false đảm bảo ứng dụng nhận được giá trị mặc định là false.
        //Từ khóa Flow: Nó giống như một đường ống dẫn nước. Bất cứ khi nào giá trị trong sổ thay đổi, thông tin mới sẽ lập tức "chảy" về phía giao diện để cập nhật màn hình tương ứng.
    }

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)//Tạo ra một file dữ liệu nhỏ trên máy điện thoại với tên được đặt trong Constants.USER_SETTINGS (ví dụ: "user_settings").
//Cơ chế: Nó giống như một cuốn sổ ghi chép bí mật của ứng dụng, nơi lưu các cặp "Tên - Giá trị" (Key-Value).
private object PreferencesKey{  //2. Định nghĩa từ khóa (PreferencesKey): Đây là việc đặt tên cho "nhãn" dữ liệu.
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    //Giải thích: Để máy tính biết bạn đang muốn đọc/ghi đúng mục "đã vào app chưa", bạn cần một cái tên cố định.
// Ở đây bạn dùng kiểu booleanPreferencesKey vì trạng thái này chỉ có 2 giá trị: Đúng (True) hoặc Sai (False).
}


/*
Tóm tắt bằng một ví dụ thực tế:
LocalUserManagerImpl: Là một nhân viên quản lý kho.
DataStore: Là cái kho chứa đồ.
PreferencesKey: Là cái nhãn dán trên thùng hàng (ghi chữ "Đã xem giới thiệu").
saveAppEntry(): Nhân viên đi dán nhãn "Xong" vào thùng hàng.
readAppEntry(): Nhân viên đứng quan sát thùng hàng đó và báo cáo cho giám đốc (App) bất kể khi nào nhãn dán thay đổi.
*/