package vfdr;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Attribute;
import weka.core.Instance;

public class NBStrategyStats extends MCStrategyStats {

	/**
	 * The naive Bayes model for this rule
	 */
	protected NaiveBayesUpdateable m_nbayes;

	protected double m_nbWeightThreshold;

	/**
	 * 
	 */
	@Override
	public double[] makePrediction(Instance inst, Attribute classAtt) throws Exception {

		boolean doNB = m_nbWeightThreshold == 0 ? true : totalWeight() > m_nbWeightThreshold;

		if (doNB){
			return m_nbayes.distributionForInstance(inst);
		}
		
		return super.makePrediction(inst,classAtt);
	}

	/**
	 * Updates the naive Bayes model and other statistics.
	 * 
	 * @param inst
	 *            The instance to update with
	 */
	@Override
	public void update(Instance inst) {
		super.update(inst);

		try {
			m_nbayes.updateClassifier(inst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
