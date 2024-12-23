## i18n (Translation) Support

### How to Test
1. Deepl 번역 API 서비스를 선택 후 키를 발급받습니다.(현 프로젝트는 deepl 번역 api로 구현되었으며, 다른 번역 api 를 사용할 경우 그 형식에 맞춰서 프로젝트 수정필요.)
2. src/main/resources/application.properties 파일을 열어 API 키 및 api url 을 입력합니다(현 프로젝트는 deepl 번역 api로 구현됨).
```properties
translate:
    base:
        apiKey: YOUR_API_KEY
        url: https://api-free.deepl.com/v2/translate?target_lang=
```
3. src/test/java/com/translation/support/infrastructure/in/web/TranslatorAPI.http 파일을 열어 번역할 문장을 입력 후 실행합니다.