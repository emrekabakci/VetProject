# Veteriner sistemi

##Projenin şuan çalıştığı halindeki UML diyagramı
<img src="/img/vetProjectUML.png" alt="Diagram_1" width="" />

##End-Pointler

- http://localhost:8080/customer/save - Customer kayıdı - POST
- http://localhost:8080/customer/list - Customer listeleme - GET
- http://localhost:8080/customer/{customerId} - Customer silme - DELETE
- http://localhost:8080/animal/save - Animal kayıdı - POST
- http://localhost:8080/animal/update - Animal güncelleme - PUT
- http://localhost:8080/animal/{customerId} - Customer Id ile Animal listeleme - GET
- http://localhost:8080/customer/searchbyname/{name} - İsim ile filtrelenmiş customer list - GET
- http://localhost:8080/vaccine/save/{animalId} - Animal id üzerinden aşı kayıdı - POST
- http://localhost:8080/vaccine/listbydaterange - Belirlenen tarih arasında aşısı olan Animal listeleme - GET ----- veri doğru geliyor ama hatalı çalışıyor
- 
