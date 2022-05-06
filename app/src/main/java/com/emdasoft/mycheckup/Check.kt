package com.emdasoft.mycheckup

class Check {

    companion object {

        fun checkIt(num: Double, ost: Double): ArrayList<String> {

            val result = arrayListOf<String>()

            val seb: Double = num * 0.1
            var res: Double = num * 0.2
            var mt: Double = num * 0.3
            var pov: Double = num * 0.4

            if (mt >= 500) mt = 500.0

            if (ost + pov >= 500) {
                pov = 500 - ost
                res = num - (pov + seb + mt)
            }

            result.add(seb.toString())
            result.add(res.toString())
            result.add(mt.toString())
            result.add(pov.toString())

            return result

        }
    }
}

