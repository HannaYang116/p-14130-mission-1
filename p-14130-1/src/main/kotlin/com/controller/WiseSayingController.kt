package com.controller

import com.entity.WiseSaying

class WiseSayingController {
    private val wiseSayings = mutableListOf<WiseSaying>()
    private var lastId = 0

    fun run() {
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")
            val command = readln().trim()

            if (command == "종료") {
                break
            }

            if (command == "등록") {
                register()
            } else if (command == "목록") {
                showList()
            } else if (command.startsWith("삭제?id=")) {
                remove(command)
            } else if (command.startsWith("수정?id=")) {
                modify(command)
            }
        }
    }

    private fun register() {
        print("명언 : ")
        val content = readln()

        print("작가 : ")
        val author = readln()

        lastId++
        wiseSayings.add(WiseSaying(lastId, content, author))
        println("${lastId}번 명언이 등록되었습니다.")
    }

    private fun showList() {
        println("번호 / 작가 / 명언")
        println("----------------------")

        for (index in wiseSayings.size - 1 downTo 0) {
            val wiseSaying = wiseSayings[index]
            println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.content}")
        }
    }

    private fun remove(command: String) {
        val id = getId(command)
        val wiseSaying = findById(id)

        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        wiseSayings.remove(wiseSaying)
        println("${id}번 명언이 삭제되었습니다.")
    }

    private fun modify(command: String) {
        val id = getId(command)
        val wiseSaying = findById(id)

        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        println("명언(기존) : ${wiseSaying.content}")
        print("명언 : ")
        wiseSaying.content = readln()

        println("작가(기존) : ${wiseSaying.author}")
        print("작가 : ")
        wiseSaying.author = readln()
    }

    private fun getId(command: String): Int? {
        return command.substringAfter("id=").toIntOrNull()
    }

    private fun findById(id: Int?): WiseSaying? {
        for (wiseSaying in wiseSayings) {
            if (wiseSaying.id == id) {
                return wiseSaying
            }
        }

        return null
    }
}
