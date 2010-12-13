/*
 * Copyright (c) 2010 Stiftung Deutsches Elektronen-Synchrotron,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS.
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE.
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS,
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION,
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */
package org.csstudio.domain.desy.calc;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;


/**
 * Accumulation cache that implements a cumulative average for the accumulated values.
 * The returned value on {@link CumulativeAverageCache#getValue()} is the sum over all
 * accumulated values divided by their number.
 *
 *
 * @author bknerr
 * @since 26.11.2010
 */
public class CumulativeAverageCache extends AbstractAccumulatorCache<Double, Double> {

    /**
     * Constructor.
     */
    public CumulativeAverageCache() {
        super(Double.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CheckForNull
    protected Double calculateAccumulation(@CheckForNull final Double accVal,
                                           @Nonnull final Double nextVal) {

        if (accVal != null) {
            return accVal + nextVal;
            // better to calc division once on #getValue invocation instead of this:
            //final int n = getNumberOfAccumulations();
            //result = curVal + (nextVal - curVal)/(n + 1);
        } else {
            return nextVal;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CheckForNull
    public Double getValue() {
        final Double accVal = super.getValue();
        if (accVal != null) {
            final int n = getNumberOfAccumulations();
            if (n > 0) {
                return accVal / n;
            }
        }
        return null;
    }
}
