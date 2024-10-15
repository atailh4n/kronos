# Kronos - Java ile yazılmış yorumlayıcılı dil

Kronos, Ata İlhan Köktürk tarafından geliştirilen kendi yazılım dili ve yorumlayıcısı olan bir projedir. Bu proje,
kullanıcıların kendi kodlarını yazmalarını ve
çalıştırmalarını sağlayan bir yapı sunmaktadır. Kronos, **Async** dilini temel alarak, programlamayı öğrenmeyi ve
uygulamayı kolaylaştırmayı hedefler. Proje halen geliştirilme aşamasındadır.

## Özellikler

- **Kendi Kodlama Dili:** Kronos, kullanıcıların kendi komutlarını tanımlayabildiği bir dil sunar.
- **Yorumlayıcı:** Yazdığınız kodları anında yorumlayarak çalıştırma imkanı.
- **Fonksiyonel Programlama:** Kullanıcılar, fonksiyonlar tanımlayabilir ve bu fonksiyonları çağırabilir.
- **Statik Metot Çağrıları:** Sınıf ve metot yapısını destekleyerek daha organize bir kod yazımına olanak tanır.
- **Async Desteği:** Asenkron programlama özellikleriyle daha verimli ve akıcı uygulamalar geliştirme imkanı.

## Kurulum

1. **Java Geliştirme Kiti (JDK) Kurulumu:**
   Kronos, Java 17 veya daha yeni bir sürüm gerektirir.
   JDK'yı [buradan](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) indirebilirsiniz.

2. **Projenin Klonlanması:**
   Terminal veya komut istemcisi kullanarak projeyi klonlayın:
   ```bash
   git clone https://github.com/atailh4n/kronos.git
   ```
3. **Proje Dizini:**
   Proje dizinine geçin
   ```bash
   cd kronos
   ```
4. **IDE üzerinden düzenleyin:**
   Projeyi Intellij ya da istediğiniz bir JDK 17 destekli IDE'de açın.

## Kullanım

Kronos dilinde yazılmış bir kodu çalıştırmak için, bir .krnos dosyası oluşturun ve örnek kodu yazın:

  ```krnos
   depends kronos.ConsoleLogFactory.Log;

   ConsoleLogFactory.Log(1234);
  ```

Bu kodu çalıştırmak için buildden sonra aşağıdaki komutu kullanın:

  ```bash
  java Main test.krnos
  ```

## Katkı Sağlama

Katkıda bulunmak isterseniz lütfen bir Pull Request açın. Katkılarınıza minnettarım :D

## Lisans

MIT lisansı var.

## İletişim

Özel bir sorunuz olursa cevap vermekten onur duyarım! Profilimdeki Instagram adresimden yazabilirsiniz.
