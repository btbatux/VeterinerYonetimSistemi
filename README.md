# 🐾 Veteriner Yönetim Sistemi

Bu proje, bir veteriner kliniğinin işlerini yönetmek için kullanılan bir API uygulamasıdır. Uygulama, veteriner doktorları, müşteriler ve hayvanlar ile ilgili çeşitli işlemleri yönetmenizi sağlar.

![Veteriner Yönetim Sistemi](https://freeimage.host/i/JPX1rrv)

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
    git clone https://github.com/btbatux/VeterinerYonetimSistemi.git
    cd VeterinerYonetimSistemi
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

## Müşteri Yönetimi

* Müşteri Ekleme

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
* Müşteri Güncelleme

URL: PUT /api/customers/{id}
```
{
    "name": "John Doe",
    "phone": "1234567890",
    "mail": "john.doe@example.com",
    "address": "123 Main St",
    "city": "Sample City"
}
```
* Müşteri Silme
URL: DELETE /api/customers/{id}


## Hayvan Yönetimi

* Hayvan Ekleme

URL: POST /api/animals
```
{
    "name": "Buddy",
    "species": "Dog",
    "breed": "Labrador",
    "gender": "Male",
    "colour": "Yellow",
    "dateOfBirth": "2021-04-15",
    "customer": {
        "id": 1
    }
}
```
* Hayvan Güncelleme

URL: PUT /api/animals/{id}
```
{
"name": "Buddy",
"species": "Dog",
"breed": "Labrador",
"gender": "Male",
"colour": "Yellow",
"dateOfBirth": "2021-04-15",
"customer": {
"id": 1
    }
}
```
* Hayvan Silme
URL: DELETE /api/animals/{id}


## Aşı Yönetimi

* Aşı Ekleme

URL: POST /api/vaccines
```
{
    "name": "Rabies Vaccine",
    "code": "RV123",
    "protection_start_date": "2023-06-01",
    "protection_finish_date": "2024-06-01",
    "animal": {
        "id": 1
    }
}
```
* Aşı Güncelleme

URL: PUT /api/vaccines/{id}
```
{
    "name": "Rabies Vaccine",
    "code": "RV123",
    "protection_start_date": "2023-06-01",
    "protection_finish_date": "2024-06-01",
    "animal": {
        "id": 1
    }
}
```

* Aşı Silme
URL: DELETE /api/vaccines/{id}

## Doktor Yönetimi

* Doktor Ekleme
URL: POST /api/doctors
```
{
    "name": "Dr. Smith",
    "phone": "0987654321",
    "mail": "dr.smith@example.com",
    "address": "456 Elm St",
    "city": "Sample City"
}
```

* Doktor Güncelleme
URL: PUT /api/doctors/{id}
```
{
    "name": "Dr. Smith",
    "phone": "0987654321",
    "mail": "dr.smith@example.com",
    "address": "456 Elm St",
    "city": "Sample City"
}
```

* Doktor Silme
URL: DELETE /api/doctors/{id}

## Randevu Yönetimi
* Randevu Ekleme
URL: POST /api/appointments

```
{
"appointmentDate": "2023-06-15T10:00:00",
"animal": {
"id": 1
},
"doctor": {
"id": 1
    }
}
```

* Randevu Güncelleme
URL: PUT /api/appointments/{id}
```
{
    "appointmentDate": "2023-06-15T10:00:00",
    "animal": {
        "id": 1
    },
    "doctor": {
        "id": 1
    }
}
```

* Randevu Silme
URL: DELETE /api/appointments/{id}

