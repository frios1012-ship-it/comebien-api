package pe.edu.upc.comebienapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    // 🔓 Endpoints públicos
    private static final String[] AUTH_WHITELIST = {
            "swagger-ui.html",
            "swagger-ui/**",
            "swagger-resources/**",
            "comebien/users/login/**",
            "comebien/users/register/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 📌 Filtro JWT antes del filtro de autenticación
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // 🌐 Habilita tu configuración CORS externa
        http.cors(Customizer.withDefaults());

        // 🚫 Deshabilita CSRF para APIs REST
        http.csrf(AbstractHttpConfigurer::disable);

        // 🔐 Reglas de acceso por endpoint
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(AUTH_WHITELIST).permitAll()

                // 🧑 CLIENTES
                .requestMatchers(HttpMethod.GET, "/comebien/clientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/clientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/clientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/clientes/**").hasRole("ADMIN")

                // 🥦 INGREDIENTES
                .requestMatchers(HttpMethod.GET, "/comebien/ingredientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/ingredientes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/ingredientes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/ingredientes/**").hasRole("ADMIN")

                // 🍽️ RECETAS
                .requestMatchers(HttpMethod.GET, "/comebien/recetas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/recetas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/recetas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/recetas/**").hasRole("ADMIN")

                // ⭐ CALIFICACIONES
                .requestMatchers(HttpMethod.GET, "/comebien/calificaciones/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/calificaciones/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/calificaciones/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/calificaciones/**").hasRole("ADMIN")

                // 💪 EJERCICIOS
                .requestMatchers(HttpMethod.GET, "/comebien/ejercicios/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/ejercicios/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/ejercicios/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/ejercicios/**").hasRole("ADMIN")

                // 🧘‍♂️ RUTINAS
                .requestMatchers(HttpMethod.GET, "/comebien/rutinas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/rutinas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/rutinas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/rutinas/**").hasRole("ADMIN")

                // 📋 LISTAS
                .requestMatchers(HttpMethod.GET, "/comebien/listas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/listas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/listas/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/listas/**").hasRole("ADMIN")

                // ❤️ FAVORITOS
                .requestMatchers(HttpMethod.GET, "/comebien/favoritos/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/comebien/favoritos/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/comebien/favoritos/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/comebien/favoritos/**").hasRole("ADMIN")

                // 📊 REPORTES
                .requestMatchers(HttpMethod.GET, "/comebien/reportes/**").hasAnyRole("USER", "ADMIN")

                // 🔒 Resto requiere autenticación
                .anyRequest().authenticated()
        );

        // ⚙️ API sin estado (JWT)
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
