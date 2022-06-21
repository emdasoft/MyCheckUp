package com.emdasoft.mycheckup

class Check {

    companion object {

        fun checkIt(num: Double, ostPov: Double, ostMt: Double, ostRes: Double): ArrayList<String> {

            val result = arrayListOf<String>()

            var seb: Double = num * 0.2
            var res: Double = num * 0.3
            var mt: Double = num * 0.2
            var pov: Double = num * 0.3

            if (ostPov + pov >= 500) {
                pov = 500 - ostPov
                res = num - (pov + seb + mt)
            }

            if (mt + ostMt >= 500) {
                mt = 500 - ostMt
                res = num - (pov + seb + mt)
            }

            if (res + ostRes >= 750) {
                res = 750 - ostRes
                seb = num - (pov + res + mt)
            }

            result.add(seb.toString())
            result.add(res.toString())
            result.add(mt.toString())
            result.add(pov.toString())

            return result

        }
    }
}

