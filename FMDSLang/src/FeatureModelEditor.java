import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

public class FeatureModelEditor {
	EPackage metamodel;

	public FeatureModelEditor(EPackage metamodel) {
		super();
		this.metamodel = metamodel;
	}
	
	public EObject createaFeature(String featureName, EObject aRootFeature) {
		EClass aFeatureClass = (EClass) metamodel.getEClassifier("Feature");
		EObject aFeature = metamodel.getEFactoryInstance().create(aFeatureClass);
		EStructuralFeature featureNameAttribute = aFeatureClass.getEStructuralFeature("featureName");
		aFeature.eSet(featureNameAttribute, featureName);
		EStructuralFeature superFeatureAttribute = aFeatureClass.getEStructuralFeature("superFeature");
		aFeature.eSet(superFeatureAttribute, aRootFeature);
		return aFeature;
	}

}



