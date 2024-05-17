# 🐾 Veteriner Yönetim Sistemi

Bu proje, bir veteriner kliniğinin işlerini yönetmek için kullanılan bir API uygulamasıdır. Uygulama, veteriner doktorları, müşteriler ve hayvanlar ile ilgili çeşitli işlemleri yönetmenizi sağlar.

![Veteriner Yönetim Sistemi](https://images.unsplash.com/photo-1608334045672-ff1f58a5b89d)

## 📋 İçindekiler
- [Özellikler](#özellikler)
- [Kurulum](#kurulum)
- [Çalıştırma](#çalıştırma)
- [Veritabanı Ayarları](#veritabanı-ayarları)
- [API Kullanımı](#api-kullanımı)
    - [Müşteri Yönetimi](#müşteri-yönetimi)
    - [Hayvan Yönetimi](#hayvan-yönetimi)
    - [Aşı Yönetimi](#aşı-yönetimi)
    - [Doktor Yönetimi](#doktor-yönetimi)
    - [Randevu Yönetimi](#randevu-yönetimi)
- [Örnek JSON Şablonları](#örnek-json-şablonları)
- [Lisans](#lisans)

## Özellikler

- Müşterileri kaydetme, güncelleme, görüntüleme ve silme
- Hayvanları kaydetme, güncelleme, görüntüleme ve silme
- Hayvan sahiplerini ve hayvanları isme göre filtreleme
- Hayvanlara uygulanan aşıları kaydetme, güncelleme, görüntüleme ve silme
- Hayvan sahiplerine ait hayvanların görüntülenmesi
- Randevu oluşturma, güncelleme, görüntüleme ve silme
- Doktorların müsait günlerini yönetme

##  Kurulum

1. Projeyi klonlayın:
    ```sh
    git clone https://github.com/kullanici_adi/veteriner-yonetim-sistemi.git
    cd veteriner-yonetim-sistemi
    ```

2. Gerekli bağımlılıkları yükleyin:
    ```sh
    ./mvnw clean install
    ```

3. Veritabanını yapılandırın (aşağıdaki Veritabanı Ayarları bölümüne bakın).

## Çalıştırma

Uygulamayı aşağıdaki komutla başlatabilirsiniz:
```sh
./mvnw spring-boot:run
```

## Veritabanı Ayarları

Veritabanı Ayarları
Uygulama, PostgreSQL veritabanı kullanmaktadır. application.properties dosyasındaki ayarları aşağıdaki gibi yapılandırın:

```
spring.application.name=Vms
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.url=jdbc:postgresql://localhost:5432/vetms
spring.datasource.username=postgres
spring.datasource.password=123123
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## API Kullanımı
Müşteri Ekleme
URL: POST /api/customers
```
{
    "name": "John Doe",
    "phone": "1234567890",
    "mail": "john.doe@example.com",
    "address": "123 Main St",
    "city": "Sample City"
}
```