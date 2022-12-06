-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 06 2022 г., 01:27
-- Версия сервера: 5.6.51
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `employee_tasks3`
--

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `name_group` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`id`, `name_group`) VALUES
(48, 'Первая группа'),
(56, 'Вторая группа'),
(78, 'Третья группа');

-- --------------------------------------------------------

--
-- Структура таблицы `group_user`
--

CREATE TABLE `group_user` (
  `id` bigint(20) NOT NULL,
  `groups_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `group_user`
--

INSERT INTO `group_user` (`id`, `groups_id`, `user_id`) VALUES
(49, 48, 12),
(50, 48, 13),
(51, 48, 14),
(57, 56, 15),
(58, 56, 16),
(79, 78, 13),
(80, 78, 14),
(81, 78, 16);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(89);

-- --------------------------------------------------------

--
-- Структура таблицы `meetings`
--

CREATE TABLE `meetings` (
  `id` bigint(20) NOT NULL,
  `meetings_date` date DEFAULT NULL,
  `meetings_description` varchar(255) DEFAULT NULL,
  `meetings_name` varchar(255) DEFAULT NULL,
  `meetings_time` time DEFAULT NULL,
  `meetings_url` varchar(255) DEFAULT NULL,
  `groups_id` bigint(20) DEFAULT NULL,
  `typemeetings_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `meetings`
--

INSERT INTO `meetings` (`id`, `meetings_date`, `meetings_description`, `meetings_name`, `meetings_time`, `meetings_url`, `groups_id`, `typemeetings_id`, `user_id`) VALUES
(1, '2022-12-14', 'Обсуждение по невыполненной отчетности', 'Деловая встреча', '13:53:46', 'https://meet.google.com/ooq-fkhx-csk', 48, 2, 11),
(2, '2022-12-23', 'Переговорим по поводу отчетности с глазу на глаз', 'Деловая встреча 2', '14:00:28', '', 56, 1, 11),
(4, '2022-12-22', 'Успеваемость', 'Деловая встреча 3', '18:54:52', '', 78, 1, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `messanger`
--

CREATE TABLE `messanger` (
  `id` bigint(20) NOT NULL,
  `message_content` varchar(255) DEFAULT NULL,
  `tasks_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `messanger`
--

INSERT INTO `messanger` (`id`, `message_content`, `tasks_id`, `user_id`) VALUES
(1, 'Привет', 52, 11),
(71, 'всем', 52, 11),
(73, 'Привет', 52, 13),
(86, 'Кажется вы рано закрыли задание', 52, 13),
(87, 'Спасибо за столь приятное задание', 82, 13);

-- --------------------------------------------------------

--
-- Структура таблицы `notes`
--

CREATE TABLE `notes` (
  `id` bigint(20) NOT NULL,
  `date_notes` date DEFAULT NULL,
  `dead_line_date` date DEFAULT NULL,
  `dead_line_time` time DEFAULT NULL,
  `description_notes` varchar(255) DEFAULT NULL,
  `name_notes` varchar(255) DEFAULT NULL,
  `time_notes` time DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `notes`
--

INSERT INTO `notes` (`id`, `date_notes`, `dead_line_date`, `dead_line_time`, `description_notes`, `name_notes`, `time_notes`, `user_id`) VALUES
(2, '2022-12-05', '2022-12-15', '20:50:38', 'Пишу себе заметку', 'Новая заметка', '21:13:08', 13),
(6, '2022-12-05', '2022-12-29', '21:12:12', 'Надо прибрать рабочее место', 'Убраться', '21:12:50', 13);

-- --------------------------------------------------------

--
-- Структура таблицы `privatedata`
--

CREATE TABLE `privatedata` (
  `id` bigint(20) NOT NULL,
  `employee_address` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `telephone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `privatedata`
--

INSERT INTO `privatedata` (`id`, `employee_address`, `number`, `series`, `telephone_number`) VALUES
(5, 'Худшая улица, 25', '546897', '4518', '89067453344'),
(36, 'Улица Пушнкина, 2', '345678', '4518', '89036228899'),
(37, 'улица Дмитрия, 9', '554678', '4518', '89652450987'),
(38, 'Улица колотушкина, 9', '567432', '4518', '89034567865'),
(45, 'Улица Романовых, 55', '567893', '4518', '89156753456'),
(46, 'ул Веселая, 18', '645789', '4518', '89156783456'),
(47, 'Улица Васильевская, 10', '564781', '4518', '89035678934'),
(88, 'Наикрутейшая улица, 11', '356789', '4518', '89653826578');

-- --------------------------------------------------------

--
-- Структура таблицы `status`
--

CREATE TABLE `status` (
  `id` bigint(20) NOT NULL,
  `name_status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `status`
--

INSERT INTO `status` (`id`, `name_status`) VALUES
(1, 'Назначено'),
(2, 'Возвращено'),
(3, 'Принято'),
(4, 'Закрыто'),
(5, 'Отказался'),
(6, 'Выполнил');

-- --------------------------------------------------------

--
-- Структура таблицы `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL,
  `dead_line_date` date DEFAULT NULL,
  `dead_line_time` time DEFAULT NULL,
  `description_task` varchar(255) DEFAULT NULL,
  `groups_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `tasks`
--

INSERT INTO `tasks` (`id`, `dead_line_date`, `dead_line_time`, `description_task`, `groups_id`, `status_id`, `user_id`) VALUES
(52, '2022-12-21', '01:13:40', 'Написать отчет ', 48, 4, 11),
(59, '2022-12-14', '01:14:06', 'Написать отчет трижды', 56, 1, 11),
(82, '2022-12-14', '16:54:28', 'Написать отчет пять раз', 78, 1, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `type_meetings`
--

CREATE TABLE `type_meetings` (
  `id` bigint(20) NOT NULL,
  `meetings_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `type_meetings`
--

INSERT INTO `type_meetings` (`id`, `meetings_type`) VALUES
(1, 'Очная форма'),
(2, 'Дистанционная форма');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `privatedata_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `first_name`, `last_name`, `middle_name`, `password`, `username`, `privatedata_id`) VALUES
(6, b'1', 'Игнатов', 'Никита', 'Сергеевич', '$2a$08$/sGhOgAjN2xvvxFC8C9e6.u7sY19NczD9MOn9WtP5bpzG.B2g8xN.', 'Ign@mail.ru', 5),
(11, b'1', 'Аксенов', 'Андрей', 'Алексеевич', '$2a$08$xJ0swPwQsB6LOAkmp7l88enS/UcCMtQZ8U6S4ZrOAFb.0Z3WHLQRa', 'Aks@mail.ru', 36),
(12, b'1', 'Гордюшин', 'Дмитрий', 'Сергеевич', '$2a$08$hA7i6JFePIR05IxJGgxob.5qeZkshwtH0PUpGNCrO5U6bXcno5r/K', 'Gord@mail.ru', 37),
(13, b'1', 'Веселов', 'Михаил', 'Владимирович', '$2a$08$zMfYxLKqlTS6sUcB3MbIDeSGrAUSx1ou4xUx0HcqcHRvwOra2701u', 'Ves@mail.ru', 38),
(14, b'1', 'Романов', 'Роман', 'Романович', '$2a$08$./mLxCFRP8KyDy1trluYLe2iFO/iC9vbF95Al4FATbrtU5IUVpUwe', 'Roma@mail.ru', 45),
(15, b'1', 'Иванов', 'Иван', 'Иванович', '$2a$08$s8rimULSzJzYWMKbKqMWOupiYhav02aa6G20lGO3U/Jec8YU.oVlO', 'Ivan@mail.ru', 46),
(16, b'1', 'Васильев', 'Василий', 'Васильевич', '$2a$08$4j0k9dMlCApUy53KLmVub.WtbgrT/oYDPTdbWuViC.I.M74e8xeeK', 'Vasil@mail.ru', 47),
(17, b'1', 'Веприцкий', 'Артём', 'Николаевич', '$2a$08$0Ft9WC1GyZ3W8IqJBIqqgOuAj.vgq1VhzkOwMioVpd19kW0jHMU3K', 'Vepr@mail.ru', 88);

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(6, 'глОтдел'),
(11, 'глОтдел'),
(12, 'млОТдел'),
(13, 'млОТдел'),
(14, 'млОТдел'),
(15, 'млОТдел'),
(16, 'млОТдел'),
(17, 'Администратор');

-- --------------------------------------------------------

--
-- Структура таблицы `user_task_status`
--

CREATE TABLE `user_task_status` (
  `id` bigint(20) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `tasks_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `user_task_status`
--

INSERT INTO `user_task_status` (`id`, `status_id`, `tasks_id`, `user_id`) VALUES
(53, 3, 52, 12),
(54, 2, 52, 13),
(55, 2, 52, 14),
(60, 1, 59, 15),
(61, 1, 59, 16),
(83, 5, 82, 13),
(84, 1, 82, 14),
(85, 1, 82, 16);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `group_user`
--
ALTER TABLE `group_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6pw6i4f0725p7qflxyv3bm9xl` (`groups_id`),
  ADD KEY `FK6u7jb50qa69gr3505uttxm86x` (`user_id`);

--
-- Индексы таблицы `meetings`
--
ALTER TABLE `meetings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc0lkub4kwc92pobmw19k44b39` (`groups_id`),
  ADD KEY `FKa9oe0ycucq2ow2kgjyk39eiav` (`typemeetings_id`),
  ADD KEY `FK6s5nvkctfkgcv93i03gxgd4j6` (`user_id`);

--
-- Индексы таблицы `messanger`
--
ALTER TABLE `messanger`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKavtntc7pg9c5g95fbayp0brqb` (`tasks_id`),
  ADD KEY `FK7q1hrrvbl863fs1mmm56yx414` (`user_id`);

--
-- Индексы таблицы `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsncua5ftnqcfg0q2pg8ylcs3x` (`user_id`);

--
-- Индексы таблицы `privatedata`
--
ALTER TABLE `privatedata`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4fpfpay1iio0u0h2i7nxm94m1` (`groups_id`),
  ADD KEY `FKfmlm4rxoa19247blv9g96eacd` (`status_id`),
  ADD KEY `FKqjk25pcieac7t8833ddos0cdu` (`user_id`);

--
-- Индексы таблицы `type_meetings`
--
ALTER TABLE `type_meetings`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKesmew4ngfebvddrmbttcnpe3m` (`privatedata_id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- Индексы таблицы `user_task_status`
--
ALTER TABLE `user_task_status`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoyphlu9tj4mqcop5kat1jps3x` (`status_id`),
  ADD KEY `FKnxws9l5x2apl86jw0rm6xd74v` (`tasks_id`),
  ADD KEY `FKc2aqqgdabysck5392dyroyk3t` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `meetings`
--
ALTER TABLE `meetings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `notes`
--
ALTER TABLE `notes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `type_meetings`
--
ALTER TABLE `type_meetings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `group_user`
--
ALTER TABLE `group_user`
  ADD CONSTRAINT `FK6pw6i4f0725p7qflxyv3bm9xl` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FK6u7jb50qa69gr3505uttxm86x` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `meetings`
--
ALTER TABLE `meetings`
  ADD CONSTRAINT `FK6s5nvkctfkgcv93i03gxgd4j6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa9oe0ycucq2ow2kgjyk39eiav` FOREIGN KEY (`typemeetings_id`) REFERENCES `type_meetings` (`id`),
  ADD CONSTRAINT `FKc0lkub4kwc92pobmw19k44b39` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`);

--
-- Ограничения внешнего ключа таблицы `messanger`
--
ALTER TABLE `messanger`
  ADD CONSTRAINT `FK7q1hrrvbl863fs1mmm56yx414` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKavtntc7pg9c5g95fbayp0brqb` FOREIGN KEY (`tasks_id`) REFERENCES `tasks` (`id`);

--
-- Ограничения внешнего ключа таблицы `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `FKsncua5ftnqcfg0q2pg8ylcs3x` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK4fpfpay1iio0u0h2i7nxm94m1` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FKfmlm4rxoa19247blv9g96eacd` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  ADD CONSTRAINT `FKqjk25pcieac7t8833ddos0cdu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKesmew4ngfebvddrmbttcnpe3m` FOREIGN KEY (`privatedata_id`) REFERENCES `privatedata` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_task_status`
--
ALTER TABLE `user_task_status`
  ADD CONSTRAINT `FKc2aqqgdabysck5392dyroyk3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKnxws9l5x2apl86jw0rm6xd74v` FOREIGN KEY (`tasks_id`) REFERENCES `tasks` (`id`),
  ADD CONSTRAINT `FKoyphlu9tj4mqcop5kat1jps3x` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
