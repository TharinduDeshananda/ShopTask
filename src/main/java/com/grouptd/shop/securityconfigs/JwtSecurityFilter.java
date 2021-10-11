package com.grouptd.shop.securityconfigs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grouptd.shop.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{

            String authHeader = request.getHeader("Authorization");

            String username=null;
            String jwt=null;

            if(authHeader!=null){
                username = jwtUtil.extractUsername(authHeader);
                jwt = authHeader;
            }

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(jwtUtil.validateToken(jwt,userDetails)){

                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null,userDetails.getAuthorities());

                    userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(userToken);

                }

            }

            filterChain.doFilter(request,response);


        }catch(Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map<String,String> errorDetails = new HashMap<>();
            errorDetails.put("message","Unauthorized / bad credentials"+e.toString());
            mapper.writeValue(response.getWriter(),errorDetails);
            return;
        }

    }
}
