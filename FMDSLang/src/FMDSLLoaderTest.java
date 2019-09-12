import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FMDSLLoaderTest {
	EPackage metamodel;
	FMDSLoader aLoader;
	private FMDSLEditor aDSLEditor;
	private EPackage aDSL;
	private FMDSLSaver aSaver;
	@BeforeEach
	void setUp() throws Exception {
		 aDSLEditor = new FMDSLEditor();
		 aDSL = aDSLEditor.createFMMetamodel();
		 aSaver = new FMDSLSaver();
		 aSaver.save(aDSL, "Models/SimpleFMDSL");
		 aLoader = new FMDSLoader();
	}

	@Test
	void testLoad() {
		try {
			metamodel = aLoader.loadMetamodel("Models/SimpleFMDSL.FM");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(metamodel.getEClassifiers().size(),2);

	}

}
