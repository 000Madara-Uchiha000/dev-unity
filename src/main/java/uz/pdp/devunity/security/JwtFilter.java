package uz.pdp.devunity.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")
//                || !(request.getRequestURI().contains("auth") || request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/v3"))
            ) {
            System.out.println(authorization);
            assert authorization != null;
            String token = authorization.substring(7);
            if (jwtUtil.isValid(token)) {
                List<SimpleGrantedAuthority> authorities = jwtUtil.getAuthorities(token);
                String username = jwtUtil.getUsername(token);
                System.out.println(username);
                var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);


    }
}
