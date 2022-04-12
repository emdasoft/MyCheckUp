package com.emdasoft.mycheckup

class Check {

    companion object {
        fun checkIt(num: Double, ost: Double): ArrayList<String> {

            val result = arrayListOf<String>()

            var seb: Double = num * 0.12
            val res: Double = num * 0.2
            val mt: Double = num * 0.2
            var pov: Double = num * 0.48

            if (ost >= pov * 0.1) {
                seb = num * 0.2
                pov = num * 0.4
            }
            result.add(seb.toString())
            result.add(res.toString())
            result.add(mt.toString())
            result.add(pov.toString())

            return result

        }
    }
}

