package com.emdasoft.mycheckup.domain

class CheckIt {

    companion object {

        fun checkIt(num: Double, ostPov: Double, ostMt: Double, ostRes: Double): ArrayList<String> {

            val result = arrayListOf<String>()

            var saving: Double = num * 0.2
            var res: Double = num * 0.3
            var mt: Double = num * 0.2
            var regular: Double = num * 0.3

            if (ostPov + regular >= 500) {
                regular = 500 - ostPov
                res = num - (regular + saving + mt)
            }

            if (mt + ostMt >= 500) {
                mt = 500 - ostMt
                res = num - (regular + saving + mt)
            }

            if (res + ostRes >= 750) {
                res = 750 - ostRes
                saving = num - (regular + res + mt)
            }

            result.add(saving.toString())
            result.add(res.toString())
            result.add(mt.toString())
            result.add(regular.toString())

            return result

        }
    }
}

