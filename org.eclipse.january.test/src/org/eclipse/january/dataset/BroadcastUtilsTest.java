/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.january.dataset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class BroadcastUtilsTest {

	@Test
	public void testBroadcastStrides() {
		int[] mShape = new int[] {2,3};

		int[] shape = new int[] {3};
		Dataset view = DatasetFactory.zeros(ByteDataset.class, shape);
		List<int[]> nShapes = BroadcastUtils.broadcastShapesToMax(mShape, shape);
		view.setShape(nShapes.get(0));
		int[] strides = BroadcastUtils.createBroadcastStrides(view, mShape);

		assertArrayEquals(new int[] {0,  1}, strides);
	}

	@Test
	public void testBroadcastNullShape() {
		int[] shape = null;
		Dataset view = DatasetFactory.zeros(ByteDataset.class, shape);
		List<int[]> nShapes = BroadcastUtils.broadcastShapesToMax(null, shape);
		view.setShape(nShapes.get(0));
		int[] strides = BroadcastUtils.createBroadcastStrides(view, null);
		assertNull(strides);
	}
}
