## Notes
Because of the security changes made by Facebook recently, create/update of posts is only allowed after Facebook App Review. 
It may take up to three months. So, all tests are failed after execution.

Link to the original article:
> https://developers.apiManager.com/blog/post/2018/07/31/apiManager-app-review-update/

## How to get new access token
The only was to generate new access token is a Graph API Explorer.

Access to Facebook test account:
>Login: 11testertester1@gmail.com
>Password: testpass1

Steps:
1. Login to Facebook test account
2. Go to "My Apps" (Мои приложения) -> Testapp
3. Click on 'Tools' (Инструменты) menu item
4. Click on 'Graph API Explorer'
5. Click on 'Get token' (Получить маркер)
6. In dropdown menu select "Get user access token" (Получить маркер доступа пользователя)
7. Copy new value from the "Access token" (Токен доступа) field
8. Paste copied value to "{project}/src/main/resources/config.properties" as "accessToken" parameter


Direct link to Open Graph API explorer:
https://developers.facebook.com/tools/explorer/413538109408653/