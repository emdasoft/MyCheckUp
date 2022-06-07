package com.emdasoft.mycheckup

class Check {

    companion object {

        fun checkIt(num: Double, ost: Double): ArrayList<String> {

            val result = arrayListOf<String>()

            var seb: Double = num * 0.1
            var res: Double = num * 0.3
            var mt: Double = num * 0.3
            var pov: Double = num * 0.3

            if (ost + pov >= 500) {
                pov = 500 - ost
                res = num - (pov + seb + mt)
            }

            if (mt >= 1000) {
                res += mt - 1000
                mt = 1000.0
            }

            if (res >= 1000) {
                seb += res - 1000
                res = 1000.0
            }

            result.add(seb.toString())
            result.add(res.toString())
            result.add(mt.toString())
            result.add(pov.toString())

            return result

        }
    }
}

