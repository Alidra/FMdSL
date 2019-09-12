import org.eclipse.emf.ecore.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
public class FMDSLEditor {
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		FMDSLEditor creator = new FMDSLEditor();
		
	}

	public EPackage createFMMetamodel() {
		// Creation du meta model par instantiation du EPackage et population avec des classifiers
		EPackage FMDLPackage = EcoreFactory.eINSTANCE.createEPackage();
		ArrayList<EClass> eClasses = new ArrayList<EClass>();
		ArrayList<EAttribute> eAttributes = new ArrayList<EAttribute>();
		
		EAttribute anEAttribute = createEAttribute("featureName", "getEString");		
		eAttributes.add(anEAttribute);
		EClass anEClass = createEClass("Feature", eAttributes);
		eAttributes = new ArrayList<EAttribute>();

		EReference anEReference = createEReference(anEClass,anEClass,"superFeature");
		anEClass.getEStructuralFeatures().add(anEReference);

		eClasses.add(anEClass);
		
		anEAttribute = createEAttribute("ConstraintID", "getEString");	
		eAttributes.add(anEAttribute);
		anEClass = createEClass("Constraint", eAttributes);
		eClasses.add(anEClass);

		for(EClass eClass : eClasses)
			FMDLPackage.getEClassifiers().add(eClass);
		
		return FMDLPackage;
	}
	EReference createEReference(EClass eReferenceClass, EClass containerClass, String referenceName) {
		EReference anERef;
		anERef = EcoreFactory.eINSTANCE.createEReference();
		anERef.setName(referenceName);
		anERef.setEType(eReferenceClass);
		anERef.setContainment(true);
		anERef.setUpperBound(1);
		containerClass.getEStructuralFeatures().add(anERef);
		return anERef;
	}

	public EClass createEClass(String eClassName, ArrayList<EAttribute>  eClassAttributes) {
		EClass anEClass = EcoreFactory.eINSTANCE.createEClass();
		anEClass.setName(eClassName);
		for (EAttribute eAttribute : eClassAttributes)
			anEClass.getEStructuralFeatures().add(eAttribute);
		return anEClass;
	}

	public EAttribute createEAttribute(String eAttributeName, String eDataTypeMethodName)  {
			EAttribute anEAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		try {			
			EcorePackage anInstance = EcorePackage.eINSTANCE;
			anEAttribute.setName(eAttributeName);
			Method eDataTypeEcoreMethod;
			eDataTypeEcoreMethod= EcorePackage.class.getDeclaredMethod(eDataTypeMethodName, (Class<?>[]) null);
			EDataType attributeType;
			attributeType = (EDataType) eDataTypeEcoreMethod.invoke(anInstance, null);
	//		EcorePackage.eINSTANCE.getEString();
			anEAttribute.setEType(attributeType);
		
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anEAttribute;
	}

	public EReference createEReference(EClass eReferenceClass, EClass containerClass, String referenceName, String oppsiteReferenceName) {
		EReference anERef = createEReference(eReferenceClass, containerClass, referenceName);
		EReference oppositeRef = createEReference(containerClass, eReferenceClass, oppsiteReferenceName);
		oppositeRef.setUpperBound(-1);
		anERef.setEOpposite(oppositeRef);
		return anERef;
	}

}
