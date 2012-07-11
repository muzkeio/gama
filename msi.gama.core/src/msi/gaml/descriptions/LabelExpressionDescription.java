/**
 * Created by drogoul, 31 mars 2012
 * 
 */
package msi.gaml.descriptions;

import java.util.*;
import msi.gama.common.util.StringUtils;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.expressions.IExpression;
import msi.gaml.types.*;
import org.eclipse.emf.common.notify.*;

/**
 * The class LabelExpressionDescription.
 * 
 * @author drogoul
 * @since 31 mars 2012
 * 
 */
public class LabelExpressionDescription extends BasicExpressionDescription {

	static Map<String, StringConstantExpression> cache = new HashMap();

	StringConstantExpression get(final String s) {
		StringConstantExpression sc = cache.get(s);
		if ( sc == null ) {
			sc = new StringConstantExpression(s);
			cache.put(s, sc);
		}
		return sc;
	}

	String value;

	class StringConstantExpression implements IExpression {

		StringConstantExpression(final String constant) {
			value = constant;
		}

		@Override
		public Object value(final IScope scope) throws GamaRuntimeException {
			return value;
		}

		@Override
		public boolean isConst() {
			return true;
		}

		@Override
		public String toGaml() {
			return StringUtils.toGamlString(value);
		}

		@Override
		public String literalValue() {
			return value;
		}

		@Override
		public IType getContentType() {
			return Types.get(IType.STRING);
		}

		@Override
		public IType getType() {
			return Types.get(IType.STRING);
		}

		/**
		 * @see msi.gaml.expressions.IExpression#getDocumentation()
		 */
		@Override
		public String getDocumentation() {
			return "Constant string: " + value;
		}

		@Override
		public String getName() {
			return value;
		}

		/**
		 * @see msi.gaml.descriptions.IGamlDescription#dispose()
		 */
		@Override
		public void dispose() {}

		/**
		 * @see msi.gaml.descriptions.IGamlDescription#getTitle()
		 */
		@Override
		public String getTitle() {
			return "Constant string: " + value;
		}

		/**
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		@Override
		public void notifyChanged(final Notification notification) {}

		/**
		 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
		 */
		@Override
		public Notifier getTarget() {
			return null;
		}

		/**
		 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
		 */
		@Override
		public void setTarget(final Notifier newTarget) {}

		/**
		 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
		 */
		@Override
		public boolean isAdapterForType(final Object type) {
			return false;
		}

		@Override
		public void unsetTarget(final Notifier oldTarget) {}

		@Override
		public IExpression resolveAgainst(final IScope scope) {
			return this;
		}

	}

	public LabelExpressionDescription(final String label) {
		super((IExpression) null);
		value = label;
		// expression = get(value);
	}

	@Override
	public IExpressionDescription compileAsLabel() {
		return this;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public IExpression getExpression() {
		return expression == null ? get(value) : expression;
	}

	@Override
	public IExpression compile(final IDescription context) {
		return getExpression();
	}

	@Override
	public boolean equalsString(final String o) {
		return value.equals(o);
	}

}
