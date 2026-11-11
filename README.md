# 🌐 Java Tabanlı Multithreaded Web Sunucusu (Simple HTTP Web Server)

Bu proje, Kırklareli Üniversitesi Yazılım Mühendisliği Bölümü ağ programlama ödevi kapsamında geliştirilmiş, bağımsız (standalone) bir HTTP Web Sunucusu uygulamasıdır. 

Uygulama, Apache Tomcat, Jetty veya Spring Boot gibi herhangi bir üçüncü parti web kütüphanesi kullanılmadan, tamamen Java'nın çekirdek (core) `java.net` ve `java.io` kütüphaneleri üzerine inşa edilmiştir. Temel amacı, TCP/IP protokolü üzerinden HTTP isteklerinin (GET) nasıl yakalandığını, işlendiğini ve istemciye (tarayıcıya) nasıl HTTP formatında yanıt dönüldüğünü göstermektir.

---

## 🚀 Proje Özellikleri

- **Bağımsız Çalışma:** Harici hiçbir bağımlılığa (dependency) ihtiyaç duymaz. Saf Java (Vanilla Java) ile yazılmıştır.
- **Çoklu İş Parçacığı (Multithreading):** Sunucu, bağlanan her bir istemciyi (tarayıcı sekmesini) farklı bir `Thread` üzerinde işler. Bu sayede aynı anda birden fazla bağlantı isteği (eşzamanlılık) sunucuyu kilitlemeden karşılanabilir.
- **Özelleştirilmiş Port Dinleme:** Sunucu, varsayılan HTTP portu (80) yerine ödev spesifikasyonlarına uygun olarak **1989** portunu dinleyecek şekilde konfigüre edilmiştir.
- **Dinamik HTTP Yanıtı:** Gelen istekler işlenerek anında `HTTP/1.1 200 OK` başlığı ve CSS ile zenginleştirilmiş (H1, H2 başlıkları, font ve renk ayarları içeren) dinamik bir HTML sayfası üretilir.
- **Nesne Yönelimli ve Temiz Kod:** Geliştirme sürecinde SOLID prensiplerine sadık kalınmış, sorumluluklar sınıflara doğru şekilde dağıtılmıştır.

---

## 🏗️ Mimari ve Kullanılan Teknolojiler

- **Programlama Dili:** Java (JDK 8 veya üzeri)
- **Ağ İletişimi:** `java.net.ServerSocket`, `java.net.Socket`
- **Veri Akışı Yönetimi:** `java.io.BufferedReader`, `java.io.PrintWriter`, `java.io.InputStreamReader`
- **Mimari Model:** İstemci-Sunucu (Client-Server) Mimarisi

### SOLID Prensipleri Entegrasyonu
Proje kodlanırken sürdürülebilirlik açısından şu kurallara dikkat edilmiştir:
1. **Single Responsibility Principle (SRP):** Kod yapısı ikiye ayrılmıştır. `SimpleWebServer` sınıfı sadece ağ bağlantılarını (Socket) kabul etmek ve Thread başlatmaktan sorumluyken; `ClientHandler` sınıfı sadece veri okuma ve HTML yanıtı gönderme işlemlerinden sorumludur.
2. **Open/Closed Principle (OCP):** İstekleri işleyen `ClientHandler` sınıfı, `Runnable` arayüzünü (interface) uygular. Gelecekte sisteme yeni yanıt türleri (örneğin JSON dönen yeni bir Handler) eklendiğinde, ana sunucu çekirdeğinin değiştirilmesine gerek kalmaz.

---

## ⚙️ Kurulum ve Çalıştırma Talimatları

Projeyi kendi bilgisayarınızda derleyip çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### 1. Gereksinimler
- Bilgisayarınızda **Java Development Kit (JDK)** kurulu olmalıdır.
- Komut satırı (Terminal/CMD) veya IntelliJ IDEA, Eclipse, VS Code gibi bir IDE (Tümleşik Geliştirme Ortamı).

### 2. Projeyi İndirme (Klonlama)
Terminal veya Git Bash üzerinden projeyi bilgisayarınıza indirin:
```bash
git clone [https://github.com/KULLANICI_ADIN/repo-adiniz.git](https://github.com/KULLANICI_ADIN/repo-adiniz.git)
cd repo-adiniz
