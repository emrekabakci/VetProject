# Veteriner sistemi
<img src="/img/vetProjectUML.png" alt="Diagram_1" width="" />

##End-Pointler

- http://localhost:8080/customer/save - Customer kayıdı - POST
- http://localhost:8080/customer/list - Customer listeleme - GET
- http://localhost:8080/customer/update - Customer Güncelleme - PUT
- http://localhost:8080/customer/{customerId} - Customer silme - DELETE
- http://localhost:8080/customer/{id} - Id ile Customer Get - GET
- http://localhost:8080/customer/searchbyname/{name} - İsim ile filtrelenmiş customer list - GET
- http://localhost:8080/customer/{customerId}/animal - CustomerId ile filtrelenmiş Animal list - GET
- http://localhost:8080/animal/save - Animal kayıdı - POST
- http://localhost:8080/animal/list - Animal listeleme - Get
- http://localhost:8080/animal/update - Animal güncelleme - PUT
- http://localhost:8080/animal/searchbyname/{name} - İsim ile filtrelenmiş Animal list - GET
- http://localhost:8080/animal/{customerId} - Customer Id ile Animal listeleme - GET
- http://localhost:8080/animal/{id} - Id ile Animal Get - GET
- http://localhost:8080/vaccine/save/{animalId} - Animal id üzerinden aşı kayıdı - POST
- http://localhost:8080/vaccine/listbydaterange - Belirlenen tarih arasında aşısı olan Animal listeleme - GET 
- http://localhost:8080/doctor/save - Doktor Ekleme - POST
- http://localhost:8080/doctor/list - Doktor listeleme - GET
- http://localhost:8080/doctor/update - Doktor güncelleme - UPDATE
- http://localhost:8080/doctor/{id} - Doktor silme - DELETE
- http://localhost:8080/appointment/save/{doctorId}/{animalId} - Randevu kayıdı - Post
- http://localhost:8080/appointment/listbydoctorid/{doctorId} - doctorId ile doctor'a ait olan randevuları listeleme - Post
- http://localhost:8080/appointment/listbyanimalid/{animalId} - animalId ile animal'a ait olan randevuları listeleme - Post
- http://localhost:8080/appointment/update - Randevu güncelleme - PUT
- http://localhost:8080/appointment/{id} - Randevu Silme - DELETE
- http://localhost:8080/availabledate/save/{doctorid} - Doktora müsait gün ekleme - POST
- http://localhost:8080/availabledate/list - Doktora ait müsait günleri listeleme - GET
- http://localhost:8080/availabledate/update/{doctorId} - Müsait gün güncelleme - PUT
- http://localhost:8080/availabledate/{id} - Müsait gün silme - DELETE

