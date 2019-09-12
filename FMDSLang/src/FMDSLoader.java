import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class FMDSLoader {
	public EPackage loadMetamodel(String filePath) throws IOException, FileNotFoundException {
		XMIResourceImpl resource = new XMIResourceImpl();
		File source = new File(filePath);
		resource.load( new FileInputStream(source), new HashMap<Object,Object>());
		EPackage root = (EPackage) resource.getContents().get(0);
		root.getEFactoryInstance();
		System.out.println("Le metamodel " +root.getName() + " has "+root.getEClassifiers().size() + "EClassifiers");
		return root;
	}

}
