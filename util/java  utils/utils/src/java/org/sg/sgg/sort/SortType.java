package org.sg.sgg.sort;


public enum SortType {
	/**
	 * —°‘Ò≈≈–Ú
	 * Selection Sorting
	 */
	SELECTION(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int len = array.length;
			for (int i = 0; i < len; i++) {
				int selected = i;
				for (int j = i + 1; j < len; j++) {
					int compare = array[j].compareTo(array[selected]);
					if (compare != 0 && compare < 0 == ascend) {
						selected = j;
					}
				}

				exchange(array, i, selected);
			}
		}
	}),
	/**
	 * ≤Â»Î≈≈–Ú
	 * Insertion Sorting
	 */
	INSERTION(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int len = array.length;
			for (int i = 1; i < len; i++) {
				T toInsert = array[i];
				int j = i;
				for (; j > 0; j--) {
					int compare = array[j - 1].compareTo(toInsert);
					if (compare == 0 || compare < 0 == ascend) {
						break;
					}
					array[j] = array[j - 1];
				}

				array[j] = toInsert;
			}
		}
	}),

	/**
	 * √∞≈›≈≈–Ú
	 * Bubble Sorting, it's very similar with Insertion Sorting
	 */
	BUBBLE(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int length = array.length;
			int lastExchangedIdx = 0;
			for (int i = 0; i < length; i++) {
				// mark the flag to identity whether exchange happened to false
				boolean isExchanged = false;
				// last compare and exchange happened before reaching index i
				int currOrderedIdx = lastExchangedIdx > i ? lastExchangedIdx : i;
				for (int j = length - 1; j > currOrderedIdx; j--) {
					int compare = array[j - 1].compareTo(array[j]);
					if (compare != 0 && compare > 0 == ascend) {
						exchange(array, j - 1, j);
						isExchanged = true;
						lastExchangedIdx = j;
					}
				}
				// if no exchange happen means array is already in order
				if (isExchanged == false) {
					break;
				}
			}
		}
	}),

	/**
	 * ø«£®Shell£©≈≈–Ú
	 * Shell Sorting
	 */
	SHELL(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int length = array.length;
			int gap = 1;

			// use the most next to length / 3 as the first gap
			while (gap < length / 3) {
				gap = gap * 3 + 1;
			}

			while (gap >= 1) {
				for (int i = gap; i < length; i++) {
					T next = array[i];
					int j = i;
					while (j >= gap) {
						int compare = array[j - gap].compareTo(next);
						// already find its position
						if (compare == 0 || compare < 0 == ascend) {
							break;
						}

						array[j] = array[j - gap];
						j -= gap;
					}
					if (j != i) {
						array[j] = next;
					}
				}
				gap /= 3;
			}

		}
	}),

	/**
	 * πÈ≤¢≈≈–Ú
	 * Merge sorting
	 */
	MERGE(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			this.sort(array, 0, array.length - 1, ascend);
		}

		private <T extends Comparable<T>> void sort(T[] array, int lo, int hi, boolean ascend) {
			// OPTIMIZE ONE
			// if the substring's length is less than 20,
			// use insertion sort to reduce recursive invocation
			if (hi - lo < 20) {
				for (int i = lo + 1; i <= hi; i++) {
					T toInsert = array[i];
					int j = i;
					for (; j > lo; j--) {
						int compare = array[j - 1].compareTo(toInsert);
						if (compare == 0 || compare < 0 == ascend) {
							break;
						}
						array[j] = array[j - 1];
					}

					array[j] = toInsert;
				}

				return;
			}

			int mid = lo + (hi - lo) / 2;
			sort(array, lo, mid, ascend);
			sort(array, mid + 1, hi, ascend);
			merge(array, lo, mid, hi, ascend);
		}

		private <T extends Comparable<T>> void merge(T[] array, int lo, int mid, int hi, boolean ascend) {
			// OPTIMIZE TWO
			// if it is already in right order, skip this merge
			// since there's no need to do so
			int leftEndCompareToRigthStart = array[mid].compareTo(array[mid + 1]);
			if (leftEndCompareToRigthStart == 0 || leftEndCompareToRigthStart < 0 == ascend) {
				return;
			}

			@SuppressWarnings("unchecked")
			T[] arrayCopy = (T[]) new Comparable[hi - lo + 1];
			System.arraycopy(array, lo, arrayCopy, 0, arrayCopy.length);

			int lowIdx = 0;
			int highIdx = mid - lo + 1;

			for (int i = lo; i <= hi; i++) {
				if (lowIdx > mid - lo) {
					// left sub array exhausted
					array[i] = arrayCopy[highIdx++];
				} else if (highIdx > hi - lo) {
					// right sub array exhausted
					array[i] = arrayCopy[lowIdx++];
				} else if (arrayCopy[lowIdx].compareTo(arrayCopy[highIdx]) < 0 == ascend) {
					array[i] = arrayCopy[lowIdx++];
				} else {
					array[i] = arrayCopy[highIdx++];
				}
			}
		}
	}),

	/**
	 * øÏÀŸ≈≈–Ú
	 * Quick Sorting
	 */
	QUICK(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			this.sort(array, 0, array.length - 1, ascend);
		}

		private <T extends Comparable<T>> void sort(T[] array, int lo, int hi, boolean ascend) {
			if (lo >= hi) {
				return;
			}

			// int partitionIdx = partition(array, lo, hi, ascend);

			T toFinal = array[lo];
			int leftIdx = lo;
			int rightIdx = hi;

			int i = lo + 1;

			while (i <= rightIdx) {
				int compare = array[i].compareTo(toFinal);
				if (compare == 0) {
					i++;
				} else if (compare < 0 == ascend) {
					exchange(array, leftIdx++, i++);
				} else {
					exchange(array, rightIdx--, i);
				}
			}

			// partially sort left array and right array
			// no need to include the leftIdx-th to rightIdx-th elements
			// since they are already in its final position
			sort(array, lo, leftIdx - 1, ascend);
			sort(array, rightIdx + 1, hi, ascend);
		}

		/**
		 * This is the old two-way partition method.
		 * 
		 * @param array
		 * @param lo
		 * @param hi
		 * @param ascend
		 * @return partitionIdx
		 */
		@Deprecated
		@SuppressWarnings("unused")
		private <T extends Comparable<T>> int partition(T[] array, int lo, int hi, boolean ascend) {
			int leftIdx = lo;
			int rightIdx = hi + 1;

			T toFinal = array[lo];

			while (true) {
				// search from left to right to locate the element placed
				// in the wrong position which should be in the right
				while (array[++leftIdx].compareTo(toFinal) < 0 == ascend) {
					if (leftIdx >= hi) {
						break;
					}
				}

				// search from right to left to locate the element placed
				// in the wrong position which should be in the left
				while (array[--rightIdx].compareTo(toFinal) > 0 == ascend) {
					if (rightIdx <= lo) {
						break;
					}
				}

				if (leftIdx >= rightIdx) {
					break;
				} else {
					exchange(array, leftIdx, rightIdx);
				}
			}

			exchange(array, lo, rightIdx);

			return rightIdx;
		}
	}),

	/**
	 * 
	 * ∂—≈≈–Ú
	 * Heap Sorting
	 */
	HEAP(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			final int length = array.length;

			// Heap use array and below convention to form data. assume k is the
			// k-th node then the node with index that nearest to (k - 1) / 2 is
			// its
			// parent and nodes with 2 * k + 1 and 2 * k + 2 are its two
			// children

			// initialize a heap
			for (int k = (length - 2) / 2; k >= 0; k--) {
				sink(array, k, length, ascend);
			}

			for (int currentHeapSize = length; currentHeapSize > 0;) {
				exchange(array, 0, currentHeapSize - 1);
				sink(array, 0, --currentHeapSize, ascend);
			}
		}

		private <T extends Comparable<T>> void sink(T[] array, int nodeIdx, int heapSize, boolean ascend) {
			while (2 * nodeIdx + 1 < heapSize) {
				int childIdx = 2 * nodeIdx + 1;

				// find the larger one between its two children, if there is any
				if (childIdx + 1 < heapSize) {
					int childrenCompare = array[childIdx].compareTo(array[childIdx + 1]);
					if (childrenCompare != 0 && childrenCompare < 0 == ascend) {
						childIdx++;
					}
				}

				int parentChildCompare = array[nodeIdx].compareTo(array[childIdx]);
				if (parentChildCompare == 0 || parentChildCompare > 0 == ascend) {
					break;
				}

				exchange(array, nodeIdx, childIdx);

				nodeIdx = childIdx;
			}
		}
	})

	;

	private SortType(Sortable sortAlgo) {
		this.sortAlgo = sortAlgo;
	}

	private Sortable sortAlgo;

	public <T extends Comparable<T>> void sort(T[] array) {
		sortAlgo.sort(array, true);
	}

	public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
		if (array == null || array.length <= 2) {
			return;
		}
		sortAlgo.sort(array, ascend);
	}

	/**
	 * exchange the nodes specified by given indices, if the indices are equal,
	 * do nothing
	 * 
	 * @param array
	 *            array which hold the elements
	 * @param p
	 *            one index to exchange
	 * @param q
	 *            the other index to exchange
	 */
	private static void exchange(Object[] array, int p, int q) {
		if (p == q) {
			return;
		}
		Object temp = array[p];
		array[p] = array[q];
		array[q] = temp;
	}

}
