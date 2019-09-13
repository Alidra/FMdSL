import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FMDSLEditorTest {
	FMDSLEditor aCreator;
	EAttribute anEAttribute;
	ArrayList<EAttribute> eClassAttributes = new ArrayList<>();
	EClass AnEFeature;
	EPackage aMetamodel;

	@BeforeEach
	void setUp() {
		aCreator = new FMDSLEditor();
		anEAttribute = aCreator.createEAttribute("FeatureName", "getEString");
		eClassAttributes.add(anEAttribute);
		AnEFeature = aCreator.createEClass("Feature", eClassAttributes);
		aMetamodel = aCreator.createFMMetamodel();
	}

	@Test
	void testCreateAttribute() {
		assert(anEAttribute.getName()=="FeatureName");
		assertEquals(anEAttribute.getEType().getName(),"EString");
	}
	@Test
	void testCreateFeature() {
		assert(AnEFeature.getName()=="Feature");
		assertEquals(AnEFeature.getEAllAttributes().size(), 1);	
		assertEquals(AnEFeature.getEAllAttributes().get(0).getName(), "FeatureName");	
	}
	@Test
	void testCreateMetamodel_attributesOfFeature() {
		EClass FeatureClass = (EClass) aMetamodel.getEClassifier("Feature");
		assertEquals(FeatureClass.getEStructuralFeatures().size(),2);
		assertNotEquals(FeatureClass.getEStructuralFeature("superFeature"),null);
		assertNotEquals(FeatureClass.getEStructuralFeature("featureName"),null);
		
		EClass ConstraintClass = (EClass) aMetamodel.getEClassifier("Constraint");
		assertEquals(ConstraintClass.getEStructuralFeatures().size(),1);
		assertNotEquals(ConstraintClass.getEStructuralFeature("ConstraintID"),null);

	}
	@Test
	void testCreateReference() {
		EReference anEReference = aCreator.createEReference(AnEFeature, AnEFeature, "superFeature");
		
		EList<EReference> eReferences = AnEFeature.getEReferences();
		EStructuralFeature anERef = eReferences.get(0);
		assertEquals(eReferences.size(),1);
		assertEquals(anERef.getName(), "superFeature");	
		assertEquals(((ENamedElement) anERef.eContainer()).getName()
				,AnEFeature.getName());
	}

	@Test
	void testCreateMirorReference() {
		EReference anEReference = aCreator.createEReference(AnEFeature, AnEFeature, "superFeature","subFeatures");
		
		EList<EReference> eReferences = AnEFeature.getEReferences();
		assertEquals(eReferences.size(),2);
		EStructuralFeature anERef = AnEFeature.getEStructuralFeature("superFeature");
		assertEquals(((ENamedElement) anERef.eContainer()).getName()
				,AnEFeature.getName());
		
		anERef = AnEFeature.getEStructuralFeature("subFeatures");;
		assertEquals(((ENamedElement) anERef.eContainer()).getName()
				,AnEFeature.getName());

	}
}
