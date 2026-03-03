---
marp: true
theme: default
style: |
    img[alt~="center"] {
      display: block;
      margin: 0 auto;
    }
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('assets/hero-background.svg')
header: 'CEN206 Nesne Yönelimli Programlama'
footer: '![height:50px](assets/2021-10-19-15-01-36-image.png) RTEU CEN206 Hafta-16'
title: "CEN206 Nesne Yönelimli Programlama"
author: "Yazar: Dr. Öğr. Üyesi Uğur CORUH"
date:
subtitle: "Final Sınavı Haftası"
geometry: "left=2.54cm,right=2.54cm,top=1.91cm,bottom=1.91cm"
titlepage: true
titlepage-color: "FFFFFF"
titlepage-text-color: "000000"
titlepage-rule-color: "CCCCCC"
titlepage-rule-height: 4
logo: "assets/2021-10-19-15-01-36-image.png"
logo-width: 100
page-background:
page-background-opacity:
links-as-notes: true
lot: true
lof: true
listings-disable-line-numbers: true
listings-no-page-break: false
disable-header-and-footer: false
header-left:
header-center:
header-right:
footer-left: "© Telif Hakkı 2025-2026 Dr. Öğr. Üyesi Uğur CORUH"
footer-center: "© Telif Hakkı 2025-2026"
footer-right:
subparagraph: true
lang: tr-TR

math: katex
---

<!-- _backgroundColor: aqua -->

<!-- _color: orange -->

<!-- paginate: false -->

## CEN206 Nesne Yönelimli Programlama

## Hafta-16 (Final Sınavı)

#### Bahar Dönemi, 2025-2026

İndir [BELGE-PDF](ce204-week-16-final.tr.md_doc.pdf), [BELGE-DOCX](ce204-week-16-final.tr.md_word.docx), [SLAYT](ce204-week-16-final.tr.md_slide.pdf)

<iframe width=700, height=500 frameBorder=0 src="../ce204-week-16-final.tr.md_slide.html"></iframe>

---

<!-- paginate: true -->

## Hafta-16 Genel Bakış

### Final Sınavı

| Modül | Konu |
|-------|------|
| A | Final Sınavı Bilgileri |
| B | Sınav Konularına Genel Bakış |
| C | Başarılar & İletişim Bilgileri |

---

<!-- _backgroundColor: aqua -->

<!-- _color: orange -->

# Modül A: Final Sınavı Bilgileri

## Sınav Detayları ve Kurallar

---

## Sınav Tarihi ve Yeri

- **Sınav Tarihi:** Öğrenci İşleri Daire Başkanlığı tarafından ilan edilecektir
- **Sınav Saati:** Öğrenci İşleri Daire Başkanlığı tarafından ilan edilecektir
- **Sınav Yeri:** Öğrenci İşleri Daire Başkanlığı tarafından ilan edilecektir
- **Süre:** 90 dakika

Kesin tarih, saat ve yer bilgileri için Öğrenci İşleri Daire Başkanlığı tarafından yayınlanan resmi sınav programını kontrol ediniz.

---

## Sınav Formatı

- **Türü:** Yazılı sınav (kağıt üzerinde)
- **Soru Tipleri:**
  - Çoktan seçmeli sorular
  - Kısa cevaplı sorular
  - Kod okuma ve analiz
  - Kod yazma (tasarım desenleri, NYP prensipleri)
  - UML diyagram çizimi

---

## İzin Verilen / Verilmeyen Materyaller

### İzin Verilen:
- Kalem, kurşun kalem, silgi
- Öğrenci kimlik kartı (zorunlu)

### İzin Verilmeyen:
- Elektronik cihazlar (telefon, tablet, dizüstü bilgisayar, akıllı saat)
- Kitap, not veya basılı materyaller
- Hesap makinesi (aksi belirtilmedikçe)
- Her türlü iletişim cihazı

---

## Sınav Kapsamı

- Final sınavı **Hafta 1'den Hafta 15'e kadar tüm konuları** kapsamaktadır
- Ara sınavdan sonra işlenen konulara ağırlık verilecektir
- Sınav **kümülatiftir** -- önceki konular, sonraki konular bağlamında sorulabilir

---

<!-- _backgroundColor: aqua -->

<!-- _color: orange -->

# Modül B: Sınav Konularına Genel Bakış

## Temel Konular ve Ağırlıkları

---

## Derste İşlenen Konular

| Hafta | Konu | Ağırlık |
|-------|------|---------|
| 1-2 | NYP Temelleri (Kapsülleme, Kalıtım, Çok Biçimlilik, Soyutlama) | Yüksek |
| 3 | Arayüzler, Tip Sistemi, Lambda İfadeleri | Orta |
| 4 | UML ve Modelleme (Sınıf Diyagramları, Sıralı Diyagramlar) | Orta |
| 5 | PlantUML | Orta |
| 6-7 | UMPLE (Model Güdümlü Geliştirme, Durum Makineleri) | Orta |
| 9 | Tasarım Desenleri -- Yaratımsal (Factory Method, Abstract Factory, Builder, Prototype, Singleton) | Yüksek |
| 10 | Tasarım Desenleri -- Yapısal (Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy) | Yüksek |
| 11 | Tasarım Desenleri -- Davranışsal (Chain of Responsibility, Command, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor) | Yüksek |
| 12 | Kod Kokuları ve Yeniden Düzenleme Temelleri | Orta |
| 13 | Yeniden Düzenleme Teknikleri (66 teknik) | Orta |
| 14 | Örnek Çalışmalar -- Tasarım Desenleri Uygulamada | Orta |

---

## Gözden Geçirilmesi Gereken Temel Kavramlar

- **NYP Sütunları:** Kapsülleme, Kalıtım, Çok Biçimlilik, Soyutlama
- **SOLID İlkeleri:** Tek Sorumluluk, Açık/Kapalı, Liskov Yerine Geçme, Arayüz Ayrımı, Bağımlılık Tersine Çevirme
- **Tasarım Desenleri:** Her desenin amacını, yapısını ve ne zaman uygulanacağını bilin
- **UML Diyagramları:** Sınıf diyagramlarını ve sıralı diyagramları okuyabilin ve çizebilin
- **Kod Kokuları:** Yaygın kod kokularını belirleyin ve uygun yeniden düzenleme önerileri sunun
- **Temiz Kod:** İsimlendirme kuralları, fonksiyon tasarımı, yorum yazma pratikleri

---

## Çalışma İpuçları

- Her haftanın ders slaytlarını ve kod örneklerini gözden geçirin
- Elle kod yazmayı pratik edin (sınavda IDE otomatik tamamlama yok)
- Her deseni **ne zaman** ve **neden** kullanacağınızı anlamaya odaklanın, sadece yapıyı ezberlemekle yetinmeyin
- Desenler arasındaki ilişkileri gözden geçirin (ör. Abstract Factory, Factory Method kullanır)
- Kağıt üzerinde UML diyagramı çizmeyi pratik edin

---

<!-- _backgroundColor: aqua -->

<!-- _color: orange -->

# Modül C: Başarılar & İletişim Bilgileri

## Son Hatırlatmalar

---

## İletişim Bilgileri

- **Öğretim Üyesi:** Dr. Öğr. Üyesi Uğur CORUH
- **E-posta:** ugur.coruh@erdogan.edu.tr
- **Ofis Saatleri:** Lütfen ders müfredatını kontrol edin veya randevu almak için e-posta ile iletişime geçin
- **Ders Web Sitesi:** Duyurular için üniversite LMS sistemini kontrol edin

Sınav içeriği veya formatı hakkında sorularınız varsa, sınav tarihinden önce iletişime geçmekten çekinmeyiniz.

---

## Başarılar Dileriz!

- Sınavdan önce iyi bir gece uykusu alın
- Sınav yerine en az 15 dakika erken varın
- Cevaplamadan önce her soruyu dikkatlice okuyun
- Zamanınızı akıllıca yönetin -- tek bir soruda çok fazla zaman harcamayın
- Zamanınız kalırsa cevaplarınızı gözden geçirin

**Final sınavınızda en iyi şansları diliyoruz!**

---

$End-Of-Week-16-Module$
