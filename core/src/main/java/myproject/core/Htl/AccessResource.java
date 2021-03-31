package myproject.core.Htl;
import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.Resource;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class AccessResource extends WCMUsePojo {
    private List<MyProperties> myList=new ArrayList<>();

    public static final String Path="/content/myOptions";

    @Override
    public void activate() throws Exception {
        setResources();
    }

    private List<MyProperties> setResources() {
        Resource resource = getResourceResolver().getResource(Path);
        if (resource != null) {
            Iterable<Resource> children=resource.getChildren();
            String nodeText= "";
            String nodeValue = "";

            for (Resource child : children) {
                Node node = child.adaptTo(Node.class);
                try {
                    nodeText = node.getProperty("text").getValue().toString();
                    nodeValue = node.getProperty("value").getValue().toString();
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
                myList.add(new MyProperties(nodeText, nodeValue));
            }
        }
        return myList;
    }

    public List<MyProperties> getMyItemsList()
    {
        return myList;
    }
}
