import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeatureModelEditorTest {

	private FMDSLEditor aDSLEditor;
	private EPackage aDSL;
	private FeatureModelEditor anFMEditor;

	@BeforeEach
	void setUp() throws Exception {
		 aDSLEditor = new FMDSLEditor();
		 aDSL = aDSLEditor.createFMMetamodel();
		 anFMEditor = new FeatureModelEditor(aDSL);

	}

	@Test
	void testCreateaFeature() {
		EClass aFeatureClass = (EClass) aDSL.getEClassifier("Feature");
		EObject aRootFeature = anFMEditor.createaFeature("featureA", null);
		EStructuralFeature featureNameAttribute = aFeatureClass.getEStructuralFeature("featureName");
		assertEquals(aRootFeature.eGet(featureNameAttribute),"featureA");
	}
	


}
