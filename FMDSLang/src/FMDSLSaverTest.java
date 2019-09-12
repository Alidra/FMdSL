import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FMDSLSaverTest {
	FMDSLEditor aDSLEditor;
	EPackage aDSL;
	FMDSLSaver aSaver;
	@BeforeEach
	void setUp() throws Exception {
		 aDSLEditor = new FMDSLEditor();
		 aDSL = aDSLEditor.createFMMetamodel();
		 aSaver = new FMDSLSaver();
		 aSaver.save(aDSL, "Models/SimpleFMDSL");
	}

	@Test
	void test() {
		
		   try{
			   aSaver.save(aDSL, "SimpleFMDSL");
			   }
			   catch(Exception e){
			      fail("Should not have thrown any exception");
			   }
			}
	}


