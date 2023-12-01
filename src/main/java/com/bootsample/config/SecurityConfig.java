package com.bootsample.config;

//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeHttpRequests(authz -> authz
        		.requestMatchers("/").permitAll()
                .requestMatchers("/loginPage").permitAll()  // --- (4) /loginPageは、全ユーザからのアクセスを許可
   //             .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated()               // --- (5) /loginPage以外は、認証を求める

                );
        // ログイン設定
        http.formLogin(login -> login                   // --- (6) フォーム認証の有効化
                .loginPage("/loginPage")                // --- (7) ログインフォームを表示するパス
                .loginProcessingUrl("/authenticate")    // --- (8) フォーム認証処理のパス
                .usernameParameter("userName")          // --- (9) ユーザ名のリクエストパラメータ名
                .passwordParameter("password")          // --- (10) パスワードのリクエストパラメータ名
                .defaultSuccessUrl("/home")             // --- (11) 認証成功時に遷移するデフォルトのパス
                .failureUrl("/loginPage?error=true")    // --- (12) 認証失敗時に遷移するパス
        		);
        // ログアウト設定
        http.logout(logout -> logout
                .logoutSuccessUrl("/loginPage")         // --- (13) ログアウト成功時に遷移するパス
                .permitAll()                            // --- (14) 全ユーザに対してアクセスを許可
                );
        return http.build();
    }
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
