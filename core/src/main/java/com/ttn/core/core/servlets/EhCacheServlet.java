package com.ttn.core.core.servlets;

import com.ttn.core.core.services.CacheService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

@Component(service = Servlet.class)
@SlingServletPaths(
        {"/bin/cache"})
public class EhCacheServlet extends SlingAllMethodsServlet {

    @Reference
    CacheService cacheService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String[] selector = request.getRequestPathInfo().getSelectors();
        if (Arrays.stream(selector).anyMatch(("add")::equalsIgnoreCase)) {
            String key = request.getParameter("key");
            String value = request.getParameter("value");
            cacheService.addToCache(key, value);
            response.getWriter().println("added to the cache");
        }
        response.getWriter().println("Is Key Exist in cache" + cacheService.isCacheExist(request.getParameter("key")));
        if (Arrays.stream(selector).anyMatch(("clear")::equalsIgnoreCase)) {
            cacheService.cleanAllCache();
            response.getWriter().println("clear cache");
        }
    }
}
