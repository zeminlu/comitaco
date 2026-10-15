# BinomialHeap ROOPS variants

The 90 ICSE BinomialHeap subjects of `tests/icse/binomialheap/set1..set5`,
rewritten as drop-in replacements for `roops.core.objects.BinomialHeap`
(`tests/roops/core/objects/BinomialHeap.java`). As ROOPS classes they can use
the precomputed tight bounds in `xml/` (`useTightUpperBounds`). The contracts
include the 2018 fix that writes quantifier ranges as `.has(n) == true`.

They come from the `weedqueue-2018-roopsbinheap(-fixbhnode)` branches, which
kept them in `tests/icse/binomialheap`; there they broke the build, because
all 90 files declare the same class. Here they are not compiled.

Run one with `--variant` and the ROOPS driver for its method:

| File name starts with | Driver |
|---|---|
| `BinomialHeapExtractMin` | `roops.core.objects.BinomialHeapTestExtractMin` |
| `BinomialHeapFindMinimum` | `roops.core.objects.BinomialHeapTestFindMin` |
| `BinomialHeapInsert` | `roops.core.objects.BinomialHeapTestInsert` |

```bash
scripts/run-benchmark.sh \
  --variant benchmarks/binomialheap-roops-variants/set1/BinomialHeapFindMinimum1Bug11D.java \
  roops.core.objects.BinomialHeapTestFindMin
```

The ICSE-format originals and their drivers (`unittest/icse/binomialheap`)
remain the primary versions.
