import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

public class FMDSLSaver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
void save(EPackage aProject, String string) {
		
		ResourceSet aResourceSet = new ResourceSetImpl();
		aResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("FM", new XMLResourceFactoryImpl());
		URI fileURI = URI.createFileURI(new File(string+".FM").getAbsolutePath());
		Resource aResource = aResourceSet.createResource(fileURI);
		aResource.
		getContents()
		.add(aProject);
		try {
			aResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
