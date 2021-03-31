package myproject.core.servlets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property= {"sling.servlet.paths=" +"/bin/sling/servlet/registeredbyPath"})
public class RegisterServletByPath extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Hello World!!");
        response.getWriter().println("Registered the Servlet by Path");
    }
}


/*


package myproject.core.servlets;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(service = Servlet.class, property = {
        "sling.servlet.resourceTypes=" + "myproject/components/Blogs",
        "sling.servlet.selectors=" + "a1",
        "sling.servlet.extensions=" + "html"})

public class RegisterServletByResourceType extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Registered Servlet by Resource Type");
        response.getWriter().println();
        response.setContentType("text/plain");

        Resource resource = request.getResource();

        Iterable<Resource> resourceIterable = resource.getChildren();

        HashMap<String, String> map = new HashMap<>();
        String blog_title = "";
        String blog_date = "";

        for (Resource child : resourceIterable) {
            Node node = child.adaptTo(Node.class);
            try {
                blog_title = node.getProperty("title").getValue().toString();
                blog_date = node.getProperty("date").getValue().toString();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            map.put(blog_title, blog_date);

        }
        //Unsorted Map
        response.getWriter().println("Before Sorting the Map");
        response.getWriter().println();
        for (Map.Entry<String, String> data : map.entrySet()) {
            response.getWriter().println("Blog Title = " + data.getKey() +"---->"+"Creation Date = " + data.getValue());
        }
        response.getWriter().println();
        //sorting map in ascending order
        Map<String, String> sorted_map = sortMapValuesByAscendingOrder(map);
        response.getWriter().println("After Sorting the Map in Ascending Order");
        response.getWriter().println();
        for (Map.Entry<String, String> entry : sorted_map.entrySet()) {
            response.getWriter().println("Blog Title = " + entry.getKey() + "---->"+" Creation Date= " + entry.getValue());
        }
        response.getWriter().println();
        //sorting map in descending order
        Map<String, String> sorted_map_desc = sortMapValuesByDescendingOrder(map);
        response.getWriter().println("After Sorting the Map in Descending Order");
        response.getWriter().println();
        for (Map.Entry<String, String> entry : sorted_map_desc.entrySet()) {
            response.getWriter().println("Blog Title= " + entry.getKey() + "---->"+"Creation Date = " + entry.getValue());
        }

    }

    public static HashMap<String, String> sortMapValuesByAscendingOrder(HashMap<String, String> myhashMap) {
        //List to store Entries
        List<Map.Entry<String, String>> entryList =
                new LinkedList<Map.Entry<String, String>>(myhashMap.entrySet());

        // Sorted the list containing Entries
        Collections.sort(entryList, new sortDateByAscendingOrder());

        // put data from sorted list to hashmap
        HashMap<String, String> temporary_map = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : entryList) {
            temporary_map.put(entry.getKey(), entry.getValue());
        }
        return temporary_map;
    }


    public static HashMap<String, String> sortMapValuesByDescendingOrder(HashMap<String, String> newhashMap) {
        //List to store Entries
        List<Map.Entry<String, String>> new_entryList =
                new LinkedList<Map.Entry<String, String>>(newhashMap.entrySet());

        // Sorted the list containing Entries
        Collections.sort(new_entryList, new sortDateByDescendingOrder());

        // put data from sorted list to hashmap
        HashMap<String, String> temporary_map = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : new_entryList) {
            temporary_map.put(entry.getKey(), entry.getValue());
        }
        return temporary_map;
    }
}

class sortDateByAscendingOrder implements Comparator<Map.Entry<String,String>>
    {
                @Override
        public int compare(Map.Entry<String, String> entry1, Map.Entry<String, String> entry2) {
            Date date1=null;
            Date date2=null;
            try
            {
                date1= new SimpleDateFormat("dd-MM-yyyy").parse(entry1.getValue());
                date2= new SimpleDateFormat("dd-MM-yyyy").parse(entry2.getValue());
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            return date1.compareTo(date2);
        }
    }

class sortDateByDescendingOrder implements Comparator<Map.Entry<String,String>>
{
    @Override
    public int compare(Map.Entry<String, String> entry1, Map.Entry<String, String> entry2) {
        Date date1=null;
        Date date2=null;
        try
        {
            date1= new SimpleDateFormat("dd-MM-yyyy").parse(entry1.getValue());
            date2= new SimpleDateFormat("dd-MM-yyyy").parse(entry2.getValue());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date2.compareTo(date1);
    }
}



 */



