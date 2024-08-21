# Software-Eng.-Candidate-TASKS---S_TASKS_4


### Uygulama Özeti

Bu proje, GitHub ve GitLab platformlarındaki seçili (bu projede bu şekilde) veya kendinizin seçeceği repolardan commit verilerini toplayarak, Spring Boot ve PostgreSQL ile yönetilen bir veritabanında saklar. Uygulamanın amacı, geliştiricilerin yaptığı commit'leri merkezi bir yerden izlemeyi ve detaylı olarak incelemeyi sağlamaktır. Veriler, frontend tarafında Thymeleaf kullanılarak görselleştirilir.

### Ana Mantık

Uygulama, GitHub ve GitLab API'lerini kullanarak belirli bir geliştiricinin yaptığı commit'leri alır ve bunları PostgreSQL veritabanında saklar. Commit verileri, ilgili patch'lerle birlikte tutulur, bu sayede her commitin hangi değişiklikleri içerdiği de görülebilir.

### Aylık Cron İşlemleri

Uygulama, her ay belirli bir zamanda çalışacak şekilde ayarlanmış bir cron job içerir. Bu cron job, her ayın başında GitHub ve GitLab API'lerinden yeni commit verilerini çekerek veritabanını günceller. Bu sayede sürekli olarak güncel commit verilerine erişim sağlanır.


Özellikler
- Commit Listeleme: Commit hash, yazar ve commit tarihi gibi detaylarla birlikte commit listesini görüntüleyin.
- Commit Detayları: Her bir commitin detaylarını, mesajını, zaman damgasını, yazarı ve ilgili patch'leri içeren ayrıntılı bir şekilde görüntüleyin.
- Yazara Göre Filtreleme: Commitleri yazara göre filtreleyin ve belirli bir yazarın yaptığı commitleri görüntüleyin.
- Sayfalama: Büyük commit geçmişlerinde kolayca gezinmek için sayfalama özelliği.


İşte özet bir şekilde frontend ve backendde kullandığınız teknolojiler ve kullanıcının girebileceği rotaları içeren bir README:

---

 

## Teknolojiler

### Backend:
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**: Veritabanı yönetimi
- **PostgreSQL**: Veritabanı
- **Docker & Docker Compose**

### Frontend:
- **Thymeleaf**: Sunucu taraflı şablon motoru
- **CSS**: Özel stillendirme
- **HTML**: Temel yapı

## Rotalar

- **Ana Sayfa:** `GET /` - Kullanıcıyı commit listesinin bulunduğu sayfaya yönlendirir.
- **Commit Listesi:** `GET /commit-list` - Tüm commitlerin listelendiği sayfa.
- **Commit Detayları:** `GET /commit/{id}` - Belirli bir commitin detaylarının görüntülendiği sayfa.
- **Yazarın Commitleri:** `GET /developer/{authorName}` - Belirli bir yazarın commitlerinin listelendiği sayfa.
